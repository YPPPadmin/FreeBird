package com.hhsj.FreeBird.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhsj.FreeBird.pojo.*;
import com.hhsj.FreeBird.service.UserInfoService;
import com.hhsj.FreeBird.service.WantGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoDu on 2019/7/12.
 */
@Controller
public class WantGoodsController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private WantGoodsService goodsService;

    /**
     * 发布求购商品
     *
     * @param wantgoods
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addWantGoods")
    public Object addWantGoods(Wantgoods wantgoods, Model model, HttpSession session, UserinfoCutom userinfoCutom) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        wantgoods.setUid(userinfo.getId());
        int result = 0;
        Date date = new Date();
        wantgoods.setModify(date);
        wantgoods.setStatus(1);
        wantgoods.setTransaction(0);
        wantgoods.setImage(userinfo.getDormitory());
        int i1 = goodsService.findWantGoodsUid(wantgoods.getUid());
        userinfoCutom.setDateTime2(date);
        userinfoCutom.setId(userinfo.getId());
        UserinfoCutom userInfoDateTime = userInfoService.findUserInfoDateTime(userinfoCutom);
        if (userInfoDateTime == null) {
            if (i1 <= 4) {
                int i = goodsService.addWantGoods(wantgoods);
                if (i == 1) {
                    result = 1;
                } else {
                    result = 0;
                }
            } else {
                result = 3;
            }
        } else {
            return userInfoDateTime.getDateTime();
        }
        return result;
    }

    /**
     * 查询所有用户的求购信息
     *
     * @param wantgoods
     * @param model
     * @return
     */
    @RequestMapping(value = "/findWantGoodsAll")
    public String findWantGoodsAll(WantGoodsCutom wantgoods, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 10);
        List<WantGoodsCutom> wantGoodsAll = goodsService.findWantGoodsAll(wantgoods);
        PageInfo<WantGoodsCutom> pageInfo = new PageInfo<WantGoodsCutom>(wantGoodsAll);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wantGoods", wantGoodsAll);
        model.addAttribute("type", wantgoods.getType());
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        return "QGXX";
    }

    /**
     * 查询用户自己发布的求购信息
     *
     * @param wantgoods
     * @param m
     * @param session
     * @return
     */
    @RequestMapping(value = "/findWantGoodsUser")
    public String findWantGoodsUser(Wantgoods wantgoods, Model m,WantGoodsCutom goodsCutom, HttpSession session, Model model) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        m.addAttribute("user", userinfo);
        wantgoods.setUid(userinfo.getId());
        List<Wantgoods> wantGoodsUser = goodsService.findWantGoodsUser(wantgoods);
        PageInfo<Wantgoods> pageInfo = new PageInfo<Wantgoods>(wantGoodsUser);
        model.addAttribute("pageInfo", pageInfo);
        int size = wantGoodsUser.size();
        m.addAttribute("size", size);
        int size1 = wantGoodsUser.size();
        int i = 5 - size1;
        m.addAttribute("i", i);
        m.addAttribute("wantGoods", wantGoodsUser);
        session.setAttribute("user", userinfo);
        return "FBQGXX";
    }

    /**
     * 删除用户的求购信息
     *
     * @param wid
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteWantGoods")
    public String deleteWantGoods(Integer wid, Integer id) {
        int result = 0;
        int i = goodsService.deleteMywantgood(wid);
        int i2 = goodsService.deleteWantgoodsMessage(wid);
        int i3 = goodsService.deleteWantGoods(id);
        if (i3 == 1) {
            result = 1;
        }
        return "forward:/findWantGoodsUser";
    }

    /**
     * 查询出用户要留言的用户名
     *
     * @param id
     * @return
     */
    Integer id1 = 0;

    @ResponseBody
    @RequestMapping(value = "/findWantGoodsId")
    public WantGoodsCutom findWantGoodsId(Integer id) {
        WantGoodsCutom wantGoodsId = goodsService.findWantGoodsId(id);
        id1 = wantGoodsId.getId();//求购商品信息ID
        return wantGoodsId;
    }

    /**
     * 给用户留言
     *
     * @param wantgoodsmessage
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/AddWantGoodsMessage")
    public int AddWantGoodsMessage(Wantgoodsmessage wantgoodsmessage, HttpSession session) {
        Date date = new Date();
        wantgoodsmessage.setModify(date);
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        wantgoodsmessage.setUid(userinfo.getId());
        wantgoodsmessage.setWid(id1);
        int i = goodsService.AddWantGoodsMessage(wantgoodsmessage);
        return i;
    }

    /**
     * 查看用户的留言信息
     *
     * @param id
     * @param model
     * @return
     */
    Integer id3;
    String name;

    @RequestMapping(value = "/findWantGoodsId2")
    public String findWantGoodsId2(Integer id, Model model, String name, HttpSession session, Wantgoods wantgoods
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        id3 = id;
        this.name = name;
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 3);
        List<WantGoodsCutom> wantGoodsId2 = goodsService.findWantGoodsId2(id);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<WantGoodsCutom> pageInfo = new PageInfo<WantGoodsCutom>(wantGoodsId2);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wantGoodsId2", wantGoodsId2);
        int i2 = wantGoodsId2.size();
        if (i2 == 0) {
            i2 = 1000;
        }
        model.addAttribute("i2", i2);
        if (wantGoodsId2.size() != 0) {
            model.addAttribute("name", name);
            Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
            model.addAttribute("user", userinfo);
            wantgoods.setUid(userinfo.getId());
            List<Wantgoods> wantGoodsUser = goodsService.findWantGoodsUser(wantgoods);
            int size = wantGoodsUser.size();
            model.addAttribute("size", size);
            int size1 = wantGoodsUser.size();
            int i = 5 - size1;
            model.addAttribute("i", i);
            model.addAttribute("wantGoods", wantGoodsUser);
            session.setAttribute("user", userinfo);
            return "FBQGXX";
        } else {
            model.addAttribute("name", name);
            return "forward:/findWantGoodsUser";
        }
    }

    /**
     * 分页查询留言信息
     *
     * @param id
     * @param model
     * @param session
     * @param wantgoods
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/findWantGoodsId3")
    public String findWantGoodsId3(Integer id, Model model, HttpSession session, Wantgoods wantgoods
            , @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 3);
        List<WantGoodsCutom> wantGoodsId2 = goodsService.findWantGoodsId2(id3);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<WantGoodsCutom> pageInfo = new PageInfo<WantGoodsCutom>(wantGoodsId2);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wantGoodsId2", wantGoodsId2);
        int i2 = wantGoodsId2.size();
        if (i2 == 0) {
            i2 = 1000;
        }
        model.addAttribute("i2", i2);
        if (wantGoodsId2.size() != 0) {
            model.addAttribute("name", name);
            Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
            model.addAttribute("user", userinfo);
            wantgoods.setUid(userinfo.getId());
            List<Wantgoods> wantGoodsUser = goodsService.findWantGoodsUser(wantgoods);
            int size = wantGoodsUser.size();
            model.addAttribute("size", size);
            int size1 = wantGoodsUser.size();
            int i = 5 - size1;
            model.addAttribute("i", i);
            model.addAttribute("wantGoods", wantGoodsUser);
            session.setAttribute("user", userinfo);
            return "FBQGXX";
        } else {
            model.addAttribute("name", name);
            return "forward:/findWantGoodsUser";
        }
    }
}
