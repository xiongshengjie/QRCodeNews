package cn.xcloude.QRCodeNews.controller;


import cn.xcloude.QRCodeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:13
 */

@Controller
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/publish")
    @ResponseBody
    public Map<String, Object> publishNews(@RequestParam(value = "file", required = false) MultipartFile[] files,
                                           @RequestParam("title") String title,
                                           @RequestParam("author") String author,
                                           @RequestParam("category") String category,
                                           @RequestParam("html") String html,
                                           HttpServletRequest request) {

        return newsService.publishNews(files, title, author, category, html, request);
    }
}
