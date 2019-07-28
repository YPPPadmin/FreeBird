package com.hhsj.FreeBird.pojo;

/**
 * Created by Administered on 2019/7/11.
 */
public class GoodMessageCustom extends Goodsmessage {
    private  String userName;
     private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
