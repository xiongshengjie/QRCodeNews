package cn.xcloude.QRCodeNews.controller;


import cn.xcloude.QRCodeNews.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

  @RequestMapping("/list")
  @ResponseBody
  public Map<String, Object> listNews(@RequestParam(value = "category", required = true) int category,
                                      @RequestParam(value = "pageNum", required = true) int pageNum,
                                      @RequestParam(value = "pageCount", defaultValue = "10", required = false) int pageCount) {

    return newsService.listNews(category, pageNum, pageCount);

  }

  @RequestMapping("/getNewsById")
  @ResponseBody
  public Map<String, Object> getNewsById(@RequestParam(value = "id", required = true) String id) {
    return newsService.getNewsById(id);
  }

  @RequestMapping("/listNewsByUser")
  @ResponseBody
  public Map<String, Object> listNewsByUser(@RequestParam(value = "userId", required = true) String userId,
                                            @RequestParam(value = "pageNum", required = true) int pageNum,
                                            @RequestParam(value = "pageCount", defaultValue = "10", required = false) int pageCount) {

    return newsService.listNewsByUser(userId, pageNum, pageCount);

  }

  @RequestMapping("/delNews")
  @ResponseBody
  public Map<String, Object> delNews(@RequestParam(value = "newsId", required = true) String id) {
    return newsService.delNews(id);
  }
}
