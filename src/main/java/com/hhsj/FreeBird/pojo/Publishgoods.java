package com.hhsj.FreeBird.pojo;

import java.util.Date;

public class Publishgoods {
    private Integer id;

    private Date modify;

    private Integer state;

    private Integer state2;

    public Integer getState2() {
        return state2;
    }

    public void setState2(Integer state2) {
        this.state2 = state2;
    }

    private Integer uid;

    private Integer gid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getModify() {
        return modify;
    }

    public void setModify(Date modify) {
        this.modify = modify;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }
}