package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.Email;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by XiaoDu on 2019/6/26.
 */
@Controller
public class EmailController {

    @Resource
    private EmailService emailService;

    @ResponseBody
    @RequestMapping(value = "/addEmail")
    public int addEmail(Email email, HttpSession session) {
        Date date = new Date();
        email.setModify(date);
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        email.setUid(userinfo.getId());
        int i = emailService.addEmail(email);
        return i;
    }


}
