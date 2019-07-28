package com.hhsj.FreeBird.pojo;

/**
 * Created by JacksonYee on 2019/7/23.
 */
public class PublishgoodsCustoms extends Publishgoods {
    Goodsinfo goodsinfo;
    Userinfo userinfo;

    public Goodsinfo getGoodsinfo() {
        return goodsinfo;
    }

    public void setGoodsinfo(Goodsinfo goodsinfo) {
        this.goodsinfo = goodsinfo;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }
}
