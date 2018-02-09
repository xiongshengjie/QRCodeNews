package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.NewsClass;
import cn.xcloude.QRCodeNews.entity.NewsClassExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsClassMapper {
    int deleteByPrimaryKey(Integer classId);

    int insert(NewsClass record);

    int insertSelective(NewsClass record);

    List<NewsClass> selectByExample(NewsClassExample example);

    NewsClass selectByPrimaryKey(Integer classId);

    int updateByPrimaryKeySelective(NewsClass record);

    int updateByPrimaryKey(NewsClass record);
}