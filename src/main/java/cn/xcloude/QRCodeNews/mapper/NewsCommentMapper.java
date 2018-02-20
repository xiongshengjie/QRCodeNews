package cn.xcloude.QRCodeNews.mapper;

import cn.xcloude.QRCodeNews.entity.NewsComment;
import cn.xcloude.QRCodeNews.entity.NewsCommentExample;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(NewsComment record);

    int insertSelective(NewsComment record);

    List<NewsComment> selectByExample(NewsCommentExample example);

    NewsComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(NewsComment record);

    int updateByPrimaryKey(NewsComment record);
}