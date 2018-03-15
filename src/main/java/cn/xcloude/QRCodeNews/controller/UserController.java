package cn.xcloude.QRCodeNews.controller;

import cn.xcloude.QRCodeNews.entity.User;
import cn.xcloude.QRCodeNews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> getSmsCode(String userMobile) {
        return userService.getSmsCode(userMobile);
    }

    @RequestMapping("checkSmsCode")
    @ResponseBody
    public Map<String, Object> checkSmsCode(String userMobile, int smsCode) {
        return userService.checkSmsCode(userMobile, smsCode);
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> register(User user,
                                        @RequestParam(value = "headFile", required = false) MultipartFile headFile,
                                        HttpServletRequest request) {
        return userService.register(user, headFile, request);
    }
}
