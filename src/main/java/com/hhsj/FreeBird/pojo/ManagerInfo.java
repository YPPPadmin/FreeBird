package com.hhsj.FreeBird.pojo;

/**
 * Created by XiaoDu on 2019/6/26.
 */
public class ManagerInfo{
    private Integer id;//管理员信息表
    private String username;//管理员用户名
    private String Phone;//管理员手机号码
    private String Password;//管理员密码
    private String UserRole;//角色
    private String password1;//用于做一个占位符

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
}
