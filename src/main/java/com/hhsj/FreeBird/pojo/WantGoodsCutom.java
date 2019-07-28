package com.hhsj.FreeBird.pojo;

/**
 * Created by XiaoDu on 2019/7/12.
 */
public class WantGoodsCutom extends Wantgoods {
    private Userinfo userinfo;
    private Goodstype goodstype;
    private String userName;
    private Wantgoodsmessage wantgoodsmessage;
    private String avatar;//查询用户的头像
    private String qq;//用户的qq
    private String content;//留言的信息

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Wantgoodsmessage getWantgoodsmessage() {
        return wantgoodsmessage;
    }

    public void setWantgoodsmessage(Wantgoodsmessage wantgoodsmessage) {
        this.wantgoodsmessage = wantgoodsmessage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Goodstype getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(Goodstype goodstype) {
        this.goodstype = goodstype;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
}
