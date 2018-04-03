package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.News;
import cn.xcloude.QRCodeNews.entity.NewsExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(String newsId);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(String newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    List<News> selectByCategoryPage(int category);

    List<News> selectByUserPage(String userId);
}