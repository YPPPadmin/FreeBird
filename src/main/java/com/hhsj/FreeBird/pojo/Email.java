package com.hhsj.FreeBird.pojo;

import java.util.Date;

/**
 * Created by XiaoDu on 2019/7/30.
 */
public class Email {
    private int id;
    private String Headline;
    private String content;
    private Integer uid;
    private Integer uid2;
    private Date Modify;
    private Integer type;
    private Integer haveRead;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadline() {
        return Headline;
    }

    public void setHeadline(String headline) {
        Headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUid2() {
        return uid2;
    }

    public void setUid2(Integer uid2) {
        this.uid2 = uid2;
    }

    public Date getModify() {
        return Modify;
    }

    public void setModify(Date modify) {
        Modify = modify;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(Integer haveRead) {
        this.haveRead = haveRead;
    }
}
