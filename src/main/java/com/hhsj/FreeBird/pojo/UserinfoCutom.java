package com.hhsj.FreeBird.pojo;

import java.util.Date;

/**
 * Created by XiaoDu on 2019/7/6.
 */
public class UserinfoCutom extends Userinfo {
    private String password;
    private Date dateTime2;//判断用户是否被禁用

    public Date getDateTime2() {
        return dateTime2;
    }

    public void setDateTime2(Date dateTime2) {
        this.dateTime2 = dateTime2;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
