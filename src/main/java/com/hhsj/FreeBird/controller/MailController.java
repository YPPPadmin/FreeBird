package com.hhsj.FreeBird.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

@Controller
public class MailController {
    @Autowired
    JavaMailSender mailSender;

    /**
     * 邮箱验证
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/send")
    public String sendEmail(String qq, Model model) {
        String s = "0";
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("2190046630@qq.com");
            message.setTo(qq);
            message.setSubject("鸿鹄狮鹫有限公司");
            s = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            message.setText("[鸿鹄狮鹫]您的验证码是" + s +
                    " 如非本人操作，请忽省本邮箱。该验证码将在1分钟后失效");
            this.mailSender.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "0";
        }
        return s;
    }

}