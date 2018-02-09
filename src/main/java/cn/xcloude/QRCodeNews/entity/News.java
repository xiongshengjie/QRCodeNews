package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class News {
    private String newsId;

    private String newsTitle;

    private String newsUrl;

    private String newsAuthor;

    private Integer newsClass;

    private Date createDatetime;

    public News(String newsId, String newsTitle, String newsUrl, String newsAuthor, Integer newsClass, Date createDatetime) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.newsAuthor = newsAuthor;
        this.newsClass = newsClass;
        this.createDatetime = createDatetime;
    }

    public News() {
        super();
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId == null ? null : newsId.trim();
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl == null ? null : newsUrl.trim();
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor == null ? null : newsAuthor.trim();
    }

    public Integer getNewsClass() {
        return newsClass;
    }

    public void setNewsClass(Integer newsClass) {
        this.newsClass = newsClass;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}