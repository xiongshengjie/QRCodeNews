package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.News;
import cn.xcloude.QRCodeNews.entity.NewsExample;
import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(String newsId);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(String newsId);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);
}