package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.GoodsInfoCustom;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.UserinfoCutom;
import com.hhsj.FreeBird.pojo.Userpwd;

import java.util.List;

public interface UserinfoMapper {
    /**
     * 登录功能
     *
     * @param userinfoCutom
     * @return
     */
    UserinfoCutom findUserinfoLogin(UserinfoCutom userinfoCutom);

    /**
     * 用户注册功能
     *
     * @param userinfoCutom
     * @return
     */
    int insertUserInfoAdd(Userinfo userinfoCutom);

    /**
     * 用户密码注册功能
     *
     * @param userpwd
     * @return
     */
    int insertUserInfoPwd(Userpwd userpwd);

    /**
     * 用于查询用户注册完的uid
     *
     * @param qq
     * @return
     */
    Userinfo findUserinfoUid(String qq);

    /**
     * 判断用户输入的QQ邮箱是否注册过
     *
     * @param qq
     * @return
     */
    int findUserInfoqq(String qq);

    int findUserInfoPhone(Userinfo userinfo);

    /*
    以下是显示用户主页信息
    */
    Userinfo findUserInfoId(Integer id);

    List<GoodsInfoCustom> findGoodsInfoId(GoodsInfoCustom goodsInfoCustom);

    int findGoodsMessageGid(int gid);

    /**
     * 判断用户是否被禁用
     *
     * @param userinfoCutom
     * @return
     */
    UserinfoCutom findUserInfoDateTime(UserinfoCutom userinfoCutom);
    int updateTou(Userinfo userinfoCutom);
}