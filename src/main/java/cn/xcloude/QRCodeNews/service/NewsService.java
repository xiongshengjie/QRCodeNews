package cn.xcloude.QRCodeNews.service;


import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:12
 */

public interface NewsService {

    Map<String, Object> publishNews(MultipartFile[] files, String title, String author, String category, String html, HttpServletRequest request);
}
