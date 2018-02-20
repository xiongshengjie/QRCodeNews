package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class NewsCategory {
    private Integer categoryId;

    private String categoryName;

    private Date createDatetime;

    public NewsCategory(Integer categoryId, String categoryName, Date createDatetime) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createDatetime = createDatetime;
    }

    public NewsCategory() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}