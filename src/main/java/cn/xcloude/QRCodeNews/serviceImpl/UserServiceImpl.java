package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.constant.Constants;
import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.mapper.UserMapper;
import cn.xcloude.QRCodeNews.service.UserService;
import cn.xcloude.QRCodeNews.utils.IdUtils;
import cn.xcloude.QRCodeNews.utils.RedisUtil;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
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
    public Map<String, Object> getSmsCode(String userMobile) {

        Map<String, Object> result = new HashMap<>();

        int SMSCode = new Random().nextInt(899999) + 100000;

        try {
            SmsSingleSender sender = new SmsSingleSender(Constants.AppID, Constants.AppKey);
            SmsSingleSenderResult smsResult = sender.send(Constants.type, Constants.nationCode, userMobile, "【xcloude】您的验证码是：" + SMSCode + "，请于2分钟内填写。如非本人操作，请忽略本短信。", null, null);
            if (smsResult.result == 0){
                //成功
                redisUtil.set(userMobile, SMSCode, Constants.expireTime);
                result.put(Api.STATUS, Api.SUCCESS);
                result.put(Api.MESSAGE, "获取验证码成功");
                return result;
            } else{
                result.put(Api.STATUS, Api.SERVER_ERROR);
                result.put(Api.MESSAGE, "短信服务异常");
                return result;
            }
        } catch (Exception e) {
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE, "服务器内部错误");
            return result;
        }
    }

    @Override
    public Map<String, Object> checkSmsCode(String userMobile, int smsCode) {
        Map<String, Object> result = new HashMap<>();

        Integer realSmsCode = (Integer) redisUtil.get(userMobile);
        if (realSmsCode != null) {
            if (smsCode == realSmsCode) {
                result.put(Api.STATUS, Api.SUCCESS);
                result.put(Api.MESSAGE, "验证成功");
            } else {
                result.put(Api.STATUS, Api.SERVER_ERROR);
                result.put(Api.MESSAGE, "验证码错误");
            }
        } else {
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE, "验证码已失效");
        }
        return result;
    }

    @Override
    public Map<String, Object> register(User user) {

        Map<String, Object> result = new HashMap<>();

        String id = IdUtils.getUUID();
        user.setUserId(id);
        int flag = 0;
        try {
            flag = userMapper.insertSelective(user);
        } catch (Exception e) {
            logger.error("user插入出错：" + e);
            result.put(Api.STATUS, Api.USER_ERROR);
            result.put(Api.MESSAGE, "手机号已被注册");
            return result;
        }
        if (flag <= 0) {
            result.put(Api.STATUS, Api.SERVER_ERROR);
            result.put(Api.MESSAGE, "注册失败");
        } else {
            result.put(Api.STATUS, Api.SUCCESS);
            result.put(Api.MESSAGE, "注册成功");
            result.put("result", user);
        }
        return result;
    }


}
