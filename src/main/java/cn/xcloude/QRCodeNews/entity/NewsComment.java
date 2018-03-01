package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class NewsComment {
    private Integer commentId;

    private String newsId;

    private String comment;

    private String userIdFrom;

    private String userIdTo;

    private Date createDatetime;

    public NewsComment(Integer commentId, String newsId, String comment, String userIdFrom, String userIdTo, Date createDatetime) {
        this.commentId = commentId;
        this.newsId = newsId;
        this.comment = comment;
        this.userIdFrom = userIdFrom;
        this.userIdTo = userIdTo;
        this.createDatetime = createDatetime;
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

    public String getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(String userIdFrom) {
        this.userIdFrom = userIdFrom == null ? null : userIdFrom.trim();
    }

    public String getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(String userIdTo) {
        this.userIdTo = userIdTo == null ? null : userIdTo.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}