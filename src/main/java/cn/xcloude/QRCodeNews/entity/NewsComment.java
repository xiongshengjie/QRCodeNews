package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class NewsComment {
  private Integer commentId;

  private String newsId;

  private String comment;

  private Integer replyCount;

  private Integer replyCommentId;

  private Integer rootCommentId;

  private String userId;

  private Date createDatetime;

  private Date updateDatetime;

  public NewsComment(Integer commentId, String newsId, String comment, Integer replyCount, Integer replyCommentId, Integer rootCommentId, String userId, Date createDatetime, Date updateDatetime) {
    this.commentId = commentId;
    this.newsId = newsId;
    this.comment = comment;
    this.replyCount = replyCount;
    this.replyCommentId = replyCommentId;
    this.rootCommentId = rootCommentId;
    this.userId = userId;
    this.createDatetime = createDatetime;
    this.updateDatetime = updateDatetime;
  }

  public NewsComment() {
    super();
  }

  public Integer getCommentId() {
    return commentId;
  }

  public void setCommentId(Integer commentId) {
    this.commentId = commentId;
  }

  public String getNewsId() {
    return newsId;
  }

  public void setNewsId(String newsId) {
    this.newsId = newsId == null ? null : newsId.trim();
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment == null ? null : comment.trim();
  }

  public Integer getReplyCount() {
    return replyCount;
  }

  public void setReplyCount(Integer replyCount) {
    this.replyCount = replyCount;
  }

  public Integer getReplyCommentId() {
    return replyCommentId;
  }

  public void setReplyCommentId(Integer replyCommentId) {
    this.replyCommentId = replyCommentId;
  }

  public Integer getRootCommentId() {
    return rootCommentId;
  }

  public void setRootCommentId(Integer rootCommentId) {
    this.rootCommentId = rootCommentId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId == null ? null : userId.trim();
  }

  public Date getCreateDatetime() {
    return createDatetime;
  }

  public void setCreateDatetime(Date createDatetime) {
    this.createDatetime = createDatetime;
  }

  public Date getUpdateDatetime() {
    return updateDatetime;
  }

  public void setUpdateDatetime(Date updateDatetime) {
    this.updateDatetime = updateDatetime;
  }
}