package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Email;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailMapper {
    /**
     * 给出审核不通过的原因
     * @param email
     * @return
     */
    int addEmail(Email email);
}