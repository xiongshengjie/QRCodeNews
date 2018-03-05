package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.constant.Constants;
import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.mapper.UserMapper;
import cn.xcloude.QRCodeNews.service.UserService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

    @Override
    public Map<String, Object> SMSCode(String userMobile) {

        Map<String, Object> result = new HashMap<>();

        int SMSCode = new Random().nextInt(899999) + 100000;

        try {
            SmsSingleSender sender = new SmsSingleSender(Constants.AppID,Constants.AppKey);
            SmsSingleSenderResult smsResult = sender.send(Constants.type,Constants.nationCode,userMobile,Constants.shortMessage + SMSCode ,null,null);
            if(smsResult.result == 0){
                //成功
                result.put(Api.STATUS, Api.SUCCESS);
                result.put(Api.MESSAGE,"获取验证码成功");
                result.put("result",SMSCode);
                return result;
            }else {
                result.put(Api.STATUS, Api.SERVER_ERROR);
                result.put(Api.MESSAGE,"短信服务异常");
                return result;
            }
        } catch (Exception e) {
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE,"服务器内部错误");
            return result;
        }
    }
}
