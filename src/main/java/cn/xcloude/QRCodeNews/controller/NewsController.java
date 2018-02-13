package cn.xcloude.QRCodeNews.controller;


import cn.xcloude.QRCodeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/add")
    @ResponseBody
    public Map<String,Object> addNews(){
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("states",2000);
        result.put("message","OK");
        return result;
    }
}
