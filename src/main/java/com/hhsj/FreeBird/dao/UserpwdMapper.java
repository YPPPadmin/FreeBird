package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.Userpwd;
import org.springframework.stereotype.Repository;

@Repository(value = "userpwdMapper")
public interface UserpwdMapper {
    Userinfo findPhone(Userinfo userinfo);//找到绑定QQ的用户

    Userinfo findqqName(Userinfo userinfo);//数据库是否存在这个手机号

    int updatePwd(Userpwd userpwd);//找回密码

    /*以下是修改密码和查看个人信息相关代码*/
    String selectPwdById(int id);

    int updatePwd1(Userpwd userpwd);

}