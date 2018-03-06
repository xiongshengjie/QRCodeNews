package cn.xcloude.QRCodeNews.controller;

import cn.xcloude.QRCodeNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/3/5 10:00
 */

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(String userName, String passWord) {
        return userService.login(userName, passWord);
    }

    @RequestMapping("getSmsCode")
    @ResponseBody
    public Map<String, Object> register(String userMobile) {
        return userService.SMSCode(userMobile);
    }

    @RequestMapping("checkSmsCode")
    @ResponseBody
    public Map<String,Object> checkSmsCode(String userMobile,String smsCode){
        return userService.checkSmsCode(userMobile,smsCode);
    }
}
