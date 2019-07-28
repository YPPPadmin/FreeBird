package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.UserpwdMapper;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.Userpwd;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 12789 on 2019/7/6.
 */
@Service(value = "userpwdService")
public class UserpwdService {
    @Resource(name = "userpwdMapper")
    private UserpwdMapper userpwdMapper;

    public Userinfo findPhone(Userinfo userinfo) {
        Userinfo findqq = userpwdMapper.findPhone(userinfo);
        return findqq;
    }

    public Userinfo findqqName(Userinfo userinfo) {
        Userinfo findqq = userpwdMapper.findqqName(userinfo);
        return findqq;
    }

    public int updatePwd(Userpwd userpwd) {
        int i = userpwdMapper.updatePwd(userpwd);
        return i;
    }

    public String selectPwdById(int id) {
        return userpwdMapper.selectPwdById(id);
    }

    public int updatePwd1(Userpwd userpwd) {
        return userpwdMapper.updatePwd1(userpwd);
    }
}
