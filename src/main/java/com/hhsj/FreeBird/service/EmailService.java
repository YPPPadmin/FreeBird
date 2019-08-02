package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.EmailMapper;
import com.hhsj.FreeBird.pojo.Email;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by XiaoDu on 2019/6/30.
 */
@Service
public class EmailService {
    @Resource
    private EmailMapper emailMapper;

    /**
     * 给出审核不通过的原因
     * @param email
     * @return
     */
    public int addEmail(Email email) {
        return emailMapper.addEmail(email);
    }

}
