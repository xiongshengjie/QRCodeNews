package cn.xcloude.QRCodeNews.service;

import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/3/5 10:01
 */
public interface UserService {

    Map<String,Object> login(String userName, String passWord);

    Map<String,Object> SMSCode(String userMobile);
}
