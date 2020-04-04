package cn.xcloude.QRCodeNews.controller;

import cn.xcloude.QRCodeNews.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/2/23 下午 1:11
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @RequestMapping("getAllCategory")
  @ResponseBody
  public Map<String, Object> getAllCategory() {
    return categoryService.getAllCategory();
  }
}
