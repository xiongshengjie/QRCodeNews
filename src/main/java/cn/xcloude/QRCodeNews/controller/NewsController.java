package cn.xcloude.QRCodeNews.controller;


import cn.xcloude.QRCodeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author XiongShengjie
 * @date 2018/1/15 下午 3:13
 */

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;
}
