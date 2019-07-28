package com.hhsj.FreeBird.pojo;

/**
 * Created by XiaoDu on 2019/7/9.
 */
public class GoodsInfoCustom extends Goodsinfo {
    private String typeName;
    private Integer csjg;
    private String userName;
    private String qq;
    private String phone;
    private String avatar;
    private int count2;//商品评论的总条数
    private int fy;//分页
    private int view;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getFy() {
        return fy;
    }

    public void setFy(int fy) {
        this.fy = fy;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public Integer getCsjg() {
        return csjg;
    }

    public void setCsjg(Integer csjg) {
        this.csjg = csjg;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
