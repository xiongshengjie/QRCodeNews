package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class NewsComment {
    private Integer commentId;

    private String newId;

    private String comment;

    private String userIdFrom;

    private String userIdTo;

    private Date createDatetime;

    public NewsComment(Integer commentId, String newId, String comment, String userIdFrom, String userIdTo, Date createDatetime) {
        this.commentId = commentId;
        this.newId = newId;
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

    public String getNewId() {
        return newId;
    }

    public void setNewId(String newId) {
        this.newId = newId == null ? null : newId.trim();
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