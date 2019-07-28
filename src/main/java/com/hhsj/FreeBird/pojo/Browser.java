package com.hhsj.FreeBird.pojo;

import java.util.Date;

/**
 * 浏览记录实体类
 */
public class Browser {
    private Integer id;//浏览记录编号
    private Integer goodId;//商品编号
    private Integer userId;//用户编号
    private Date bornDate;//浏览日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
}
