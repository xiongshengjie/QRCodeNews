package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.mapper.UserMapper;
import cn.xcloude.QRCodeNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/3/5 10:01
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public Map<String, Object> login(String userName, String passWord) {

        Map<String, Object> result = new HashMap<>();
        User user = new User(userName, passWord);
        user = userMapper.loginByName(user);
        if (user == null) {
            result.put(Api.STATUS, Api.USER_ERROR);
            result.put(Api.MESSAGE, "用户名或密码错误");
            return result;
        }
        result.put(Api.STATUS, Api.SUCCESS);
        result.put(Api.MESSAGE, "登录成功");
        result.put("result", user);
        return result;
    }
}
