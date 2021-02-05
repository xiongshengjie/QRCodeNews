package cn.xcloude.QRCodeNews.serviceImpl;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.entity.NewsComment;
import cn.xcloude.QRCodeNews.entity.NewsCommentExample;
import cn.xcloude.QRCodeNews.mapper.NewsCommentMapper;
import cn.xcloude.QRCodeNews.service.NewsCommentService;
import cn.xcloude.QRCodeNews.utils.IdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("newsCommentService")
public class NewsCommentServiceImpl implements NewsCommentService {
  private final NewsCommentMapper commentMapper;

  public NewsCommentServiceImpl(NewsCommentMapper commentMapper) {
    this.commentMapper = commentMapper;
  }

  @Override
  public Map<String, Object> listComments(String newsId, int pageNum, int pageCount) {
    NewsCommentExample example = new NewsCommentExample();
    example.createCriteria().andNewsIdEqualTo(newsId).andReplyCommentIdIsNull();
    return generatePageResult(pageNum, pageCount, example);
  }

  @Override
  public Map<String, Object> listReplyComments(Integer commentId, int pageNum, int pageCount) {
    NewsCommentExample example = new NewsCommentExample();
    example.createCriteria().andRootCommentIdEqualTo(commentId);
    example.setOrderByClause("comment_id");
    return generatePageResult(pageNum, pageCount, example);
  }

  @Override
  public Map<String, Object> publishComment(NewsComment comment) {
    NewsComment mainComment;
    NewsComment rootComment = null;
    if (IdUtils.isValidId(comment.getReplyCommentId())) {
      mainComment = commentMapper.selectByPrimaryKey(comment.getReplyCommentId());
      if (mainComment == null) {
        return Api.generateResult(Api.NO_COMMENT_ERROR, "您回复的原评论已被删除");
      }

      if (IdUtils.isValidId(mainComment.getRootCommentId())) {
        rootComment = commentMapper.selectByPrimaryKey(mainComment.getRootCommentId());
        if (rootComment == null) {
          return Api.generateResult(Api.NO_COMMENT_ERROR, "您回复的根评论已被删除");
        }
      } else {
        rootComment = mainComment;
      }
    }

    if (rootComment != null) {
      comment.setRootCommentId(rootComment.getCommentId());
      rootComment.setReplyCount(rootComment.getReplyCount() + 1);
      // TODO 事物处理
      int rootCount = commentMapper.updateByPrimaryKey(rootComment);
    }

    int count = commentMapper.insertSelective(comment);
    if (count <= 0) {
      return Api.generateResult(Api.ERROR, "发表评论失败");
    } else {
      return Api.generateResult(Api.SUCCESS, "发表评论成功");
    }
  }

  @Override
  public Map<String, Object> deleteComment(Integer commentId) {
    int count = commentMapper.deleteByPrimaryKey(commentId);
    if (count <= 0) {
      return Api.generateResult(Api.ERROR, "删除失败");
    }

    return Api.generateResult(Api.SUCCESS, "删除成功");
  }

  private Map<String, Object> generatePageResult(int pageNum, int pageCount,
      NewsCommentExample example) {
    PageHelper.startPage(pageNum, pageCount);
    List<NewsComment> newsComments = commentMapper.selectByExample(example);
    PageInfo<NewsComment> pageInfo = new PageInfo<>(newsComments);

    Map<String, Object> result = new HashMap<>(2);
    result.put(Api.STATUS, Api.SUCCESS);
    result.put(Api.RESULT, pageInfo);
    return result;
  }
}
