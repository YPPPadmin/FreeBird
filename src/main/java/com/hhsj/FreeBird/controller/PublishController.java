package com.hhsj.FreeBird.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhsj.FreeBird.pojo.*;
import com.hhsj.FreeBird.service.GoodsInfoService;
import com.hhsj.FreeBird.service.PublishgoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 12789 on 2019/7/15.
 */
@Controller
public class PublishController {
    @Resource(name = "publishgoodsService")
    private PublishgoodsService publishgoodsService;

    /*
        根据与与用户id来查询用户发布的商品信息
         */
    @RequestMapping(value = "/Publishgoods")
    public String findPublishgoodsList(HttpSession session, Integer uid, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) throws Exception {
        //获取用户的id
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        uid = userinfo.getId();
        PageHelper.startPage(pageNum, 5);
        List<PublishgoodsCustom> publishgoodsList = publishgoodsService.findPublishgoodsList(uid);
        PageInfo<PublishgoodsCustom> pageInfo = new PageInfo<PublishgoodsCustom>(publishgoodsList);
        model.addAttribute("publishgoodsList", publishgoodsList);
        model.addAttribute("pageInfo", pageInfo);
        return "Publishgoods";
    }

    /*
    根据与与用户id来查询用户发布的商品信息实现状态条件查询
     */
    @RequestMapping(value = "/findPublishList")
    public String findPublishList(HttpSession session, Integer uid, Integer display, Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) throws Exception {
        //获取用户的id
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        uid = userinfo.getId();
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 5);
        List<PublishgoodsCustom> publishgoodsList;
        if (display != null) {
            publishgoodsList = publishgoodsService.findPublishList(uid, display);
        } else {
            publishgoodsList = publishgoodsService.findPublishList(uid, null);
        }
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PublishgoodsCustom> pageInfo = new PageInfo<PublishgoodsCustom>(publishgoodsList);
        model.addAttribute("publishgoodsList", publishgoodsList);
        model.addAttribute("pageInfo", pageInfo);
        //把查询条件传到页面，实现回显
        model.addAttribute("display", display);
        return "findPublishList";
    }

    /*
    点击商品名称查看发布信息的详情根据用户id和商品id
     */
    @RequestMapping(value = "/findPublishgoods")
    public String findPublishgoods(Integer uid, Integer gid, Model model) throws Exception {
        PublishgoodsCustom publishgoods = publishgoodsService.findPublishgoods(uid, gid);
        model.addAttribute("publishgoods", publishgoods);
        return "findPublishgoods";
    }

    /*
  删除关于用户发布商品的数据
  因为有主外键关系所以我们先删除外表再删除主表
   */
    @RequestMapping(value = "/deletePublishGoodsInfo")
    public String deletePublishGoodsInfo(Integer gid) throws Exception {
        publishgoodsService.deletePublishgoods(gid);
        publishgoodsService.deletegoodscollection(gid);
        publishgoodsService.deletegoodsmessage(gid);
        publishgoodsService.deleteshoppingcar(gid);
        publishgoodsService.deleteBowers(gid);
        publishgoodsService.deletebuiedgoods(gid);
        if (publishgoodsService.deletegoodsinfo(gid) >= 0) {
            return "redirect:Publishgoods";
        }
        return "redirect:Publishgoods";
    }

    //买家
    @RequestMapping(value = "/order")
    public String gotoDingDan(Model model,HttpSession session) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        List<PublishgoodsCustoms> sbcx =publishgoodsService.findSBCX(userinfo.getId());
        int size = sbcx.size();
        model.addAttribute("pdd",sbcx);
        return "order";
    }
    //卖家
    @RequestMapping(value ="/orderofmai")
    public String gotoDingDan2(Model model,HttpSession session) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        List<PublishgoodsCustoms> sbcx =publishgoodsService.findSBCX2(userinfo.getId());
        int size = sbcx.size();
        model.addAttribute("pdd",sbcx);
        return "order2";
    }

    //买家更改交易状态
    @RequestMapping(value = "/updateState2")
    @ResponseBody
    public Object updateState2(int id){
        Publishgoods p=new Publishgoods();
        p.setState2(2);
        p.setId(id);
        int data= publishgoodsService.updateState2(p);
        return data;
    }
    //卖家更改交易状态
    @RequestMapping(value = "/updateState")
    @ResponseBody
    public Object updateState(int id){
        Publishgoods p=new Publishgoods();
        p.setState(2);
        p.setId(id);
        int data= publishgoodsService.updateState(p);
        return data;
    }
    @Resource
    GoodsInfoService goodsInfoService;
    //卖家取消交易
    @RequestMapping(value = "/xuxiaoState")
    @ResponseBody
    public Object xuxiaoState(int id){
        int gid=  publishgoodsService.quxiaojiaoyi(id);
        Goodsinfo g= goodsInfoService.findGoodInfo(gid);//更改商品状态
        g.setDisplay(0);
        int data= goodsInfoService.updateDisplay(g);

        Publishgoods p=new Publishgoods();
        p.setState(0);
        p.setId(id);
        publishgoodsService.updateState(p);
        return data;
    }
}
