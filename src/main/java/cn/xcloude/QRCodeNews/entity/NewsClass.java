package cn.xcloude.QRCodeNews.entity;

import java.util.Date;

public class NewsClass {
    private Integer classId;

    private String className;

    private Date createDatetime;

    public NewsClass(Integer classId, String className, Date createDatetime) {
        this.classId = classId;
        this.className = className;
        this.createDatetime = createDatetime;
    }

    public NewsClass() {
        super();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
}