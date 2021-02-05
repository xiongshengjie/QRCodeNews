package cn.xcloude.QRCodeNews.controller;

import cn.xcloude.QRCodeNews.constant.Api;
import cn.xcloude.QRCodeNews.entity.NewsComment;
import cn.xcloude.QRCodeNews.service.NewsCommentService;
import cn.xcloude.QRCodeNews.utils.IdUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/comment")
public class NewsCommentController {
  private final NewsCommentService commentService;

  public NewsCommentController(NewsCommentService commentService) {
    this.commentService = commentService;
  }

  @RequestMapping("/list")
  @ResponseBody
  public Map<String, Object> listComments(@RequestParam(value = "newsId") String newsId,
      @RequestParam(value = "pageNum") int pageNum,
      @RequestParam(value = "pageCount", defaultValue = "10", required = false) int pageCount) {
    return commentService.listComments(newsId, pageNum, pageCount);
  }

  @RequestMapping("/listReplyComments")
  @ResponseBody
  public Map<String, Object> listReplyComments(@RequestParam(value = "commentId") Integer commentId,
      @RequestParam(value = "pageNum") int pageNum,
      @RequestParam(value = "pageCount", defaultValue = "10", required = false) int pageCount) {
    return commentService.listReplyComments(commentId, pageNum, pageCount);
  }

  @RequestMapping("/publish")
  @ResponseBody
  public Map<String, Object> publishComment(
      @RequestParam(value = "newsId") String newsId,
      @RequestParam(value = "comment") String comment,
      @RequestParam(value = "replyCommentId", required = false) Integer replyCommentId,
      @RequestParam(value = "userId") String userId) {
    if (IdUtils.isNullOrEmpty(newsId) || IdUtils.isNullOrEmpty(comment)
        || IdUtils.isNullOrEmpty(userId)) {
      return Api.generateResult(Api.REQUEST_PARAM_ERROR, "参数错误");
    }

    NewsComment newsComment = new NewsComment();
    newsComment.setNewsId(newsId);
    newsComment.setComment(comment);
    newsComment.setReplyCommentId(replyCommentId);
    newsComment.setUserId(userId);

    return commentService.publishComment(newsComment);
  }

  @RequestMapping("/delete")
  @ResponseBody
  public Map<String, Object> deleteComment(@RequestParam(value = "commentId") Integer commentId) {
    return commentService.deleteComment(commentId);
  }
}
