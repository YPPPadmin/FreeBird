package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.Goodsmessage;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.UserinfoCutom;
import com.hhsj.FreeBird.service.GoodsMessageService;
import com.hhsj.FreeBird.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administered on 2019/7/11.
 */
@Controller
public class GoodsMessageController {

    @Resource
    private GoodsMessageService messageService;
    @Resource
    private UserInfoService userInfoService;


    @ResponseBody
    @RequestMapping("/addComments")
    public Object addGoodsMeassage(HttpSession session, Goodsmessage goodsmessage, UserinfoCutom userinfoCutom) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        goodsmessage.setUid(userinfo.getId());
        goodsmessage.setModify(new Date());
        goodsmessage.setState(1);
        Date date = new Date();
        userinfoCutom.setDateTime2(date);
        userinfoCutom.setId(userinfo.getId());
        UserinfoCutom userInfoDateTime = userInfoService.findUserInfoDateTime(userinfoCutom);
        if (userInfoDateTime == null) {
            int i = messageService.addGoodsmessage(goodsmessage);
            if (i == 1) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return userInfoDateTime.getDateTime();
        }
    }
}
