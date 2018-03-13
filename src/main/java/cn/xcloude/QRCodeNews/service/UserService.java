package cn.xcloude.QRCodeNews.service;

import cn.xcloude.QRCodeNews.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/3/5 10:01
 */
public interface UserService {

    Map<String, Object> login(String userName, String passWord);

    Map<String, Object> getSmsCode(String userMobile);

    Map<String, Object> checkSmsCode(String userMobile, int smsCode);

    Map<String, Object> register(User user , MultipartFile headFile , HttpServletRequest request);
}
