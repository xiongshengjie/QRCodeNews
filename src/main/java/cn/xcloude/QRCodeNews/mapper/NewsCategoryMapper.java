package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.NewsCategory;
import cn.xcloude.QRCodeNews.entity.NewsCategoryExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    List<NewsCategory> selectByExample(NewsCategoryExample example);

    NewsCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);
}