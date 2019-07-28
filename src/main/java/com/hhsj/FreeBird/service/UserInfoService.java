package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.UserinfoMapper;
import com.hhsj.FreeBird.pojo.GoodsInfoCustom;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.UserinfoCutom;
import com.hhsj.FreeBird.pojo.Userpwd;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiaoDu on 2019/7/6.
 */
@Service("userinfoService")
public class UserInfoService {
    @Resource
    private UserinfoMapper userinfoMapper;

    /**
     * 登录功能
     *
     * @param userinfoCutom
     * @return
     */
    public UserinfoCutom findUserinfoLogin(UserinfoCutom userinfoCutom) {
        UserinfoCutom userinfoLogin = userinfoMapper.findUserinfoLogin(userinfoCutom);
        return userinfoLogin;
    }

    /**
     * 用户注册功能
     *
     * @param userinfoCutom
     * @return
     */
    public int insertUserInfoAdd(Userinfo userinfoCutom) {
        int i = userinfoMapper.insertUserInfoAdd(userinfoCutom);
        return i;
    }

    /**
     * 用户注册密码功能
     *
     * @param userpwd
     * @return
     */
    public int insertUserInfoPwd(Userpwd userpwd) {
        int i = userinfoMapper.insertUserInfoPwd(userpwd);
        return i;
    }

    /**
     * 用于查询用户注册完的uid
     *
     * @param qq
     * @return
     */
    public Userinfo findUserInfoUid(String qq) {
        Userinfo userinfo = userinfoMapper.findUserinfoUid(qq);
        return userinfo;
    }

    /**
     * 判断用户输入的QQ邮箱是否注册过
     *
     * @param qq
     * @return
     */
    public int findUserInfoqq(String qq) {
        int count = userinfoMapper.findUserInfoqq(qq);
        return count;
    }

    public int findUserInfoPhone(Userinfo userinfo) {
        int userInfoPhone = userinfoMapper.findUserInfoPhone(userinfo);
        return userInfoPhone;
    }

    /*以下是显示用户主页信息*/
    public Userinfo findUserInfoId(Integer id) {
        Userinfo userInfoId = userinfoMapper.findUserInfoId(id);
        return userInfoId;
    }

    public List<GoodsInfoCustom> findGoodsInfoId(GoodsInfoCustom goodsInfoCustom) {
        List<GoodsInfoCustom> goodsInfoId = userinfoMapper.findGoodsInfoId(goodsInfoCustom);
        return goodsInfoId;
    }

    public int findGoodsMessageGid(int gid) {
        int goodsMessageGid = userinfoMapper.findGoodsMessageGid(gid);
        return goodsMessageGid;
    }

    /**
     * 判断用户是否被禁用
     * @param userinfo
     * @return
     */
    public UserinfoCutom findUserInfoDateTime(UserinfoCutom userinfo) {
        UserinfoCutom userInfoDateTime = userinfoMapper.findUserInfoDateTime(userinfo);
        return userInfoDateTime;
    }
    public int update(Userinfo userinfoCutom){
        return  userinfoMapper.updateTou(userinfoCutom);
    }
}
