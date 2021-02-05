package cn.xcloude.QRCodeNews.service;

import cn.xcloude.QRCodeNews.entity.NewsComment;

import java.util.Map;

public interface NewsCommentService {
  Map<String, Object> listComments(String newsId, int pageNum, int pageCount);

  Map<String, Object> listReplyComments(Integer commentId, int pageNum, int pageCount);

  Map<String, Object> publishComment(NewsComment comment);

  Map<String, Object> deleteComment(Integer commentId);
}
