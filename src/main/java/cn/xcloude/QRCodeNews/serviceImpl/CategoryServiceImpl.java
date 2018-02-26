package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.entity.NewsCategory;
import cn.xcloude.QRCodeNews.mapper.NewsCategoryMapper;
import cn.xcloude.QRCodeNews.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author XiongShengjie
 * @date 2018/2/23 下午 1:13
 */

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private NewsCategoryMapper categoryMapper;

    public Map<String, Object> getAllCategory() {
        Map<String, Object> result = new HashMap<>();
        List<NewsCategory> categories = categoryMapper.selectByExample(null);
        if (categories == null || categories.size() <= 0) {
            result.put(Api.STATUS, Api.ERROR);
            result.put(Api.MESSAGE, "获取分类失败");
            return result;
        }
        result.put(Api.STATUS,Api.SUCCESS);
        result.put(Api.MESSAGE,"获取分类成功");
        result.put("categories",categories);
        return result;
    }

}
