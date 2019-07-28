package com.hhsj.FreeBird.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhsj.FreeBird.pojo.BuiedgoodsCustom;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.service.BuiedgoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 12789 on 2019/7/15.
 */
@Controller
public class BuiedController {
    @Resource(name = "buiedgoodsService")
    private BuiedgoodsService buiedgoodsService;
    /*
       根据用户的id来查询用户的订单
       */

    public String findOrderList(HttpSession session, Model model, Integer uid, Integer state, @RequestParam(defaultValue = "1", value = "pageNum")Integer pageNum) throws Exception {
        //获取对应用户的id
        Userinfo userinfo =(Userinfo)session.getAttribute("userinfoLogins");
        uid=userinfo.getId();
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 2);
        List<BuiedgoodsCustom> bUiedgoodsList;
        if(state !=null ){
            bUiedgoodsList = buiedgoodsService.findBuiedgoodsList(uid,state);
            //把查询条件传到页面，实现回显
            model.addAttribute("state",state);
        }else{
            bUiedgoodsList = buiedgoodsService.findBuiedgoodsList(uid,null);
        }
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<BuiedgoodsCustom> pageInfo=new PageInfo<BuiedgoodsCustom>(bUiedgoodsList);
        model.addAttribute("bUiedgoodsList",bUiedgoodsList);
        model.addAttribute("pageInfo", pageInfo);
        return "order";
    }
    /*
   点击商品名称查看订单详情根据用户id和商品id
    */
    @RequestMapping(value = "/findBuiedgoods")
    public String findBuiedgoods(Integer uid,Integer gid,Model model) throws Exception {
        BuiedgoodsCustom buiedgoods = buiedgoodsService.findBuiedgoods(uid, gid);
        model.addAttribute("buiedgoods",buiedgoods);
        return "findBuiedgoods";
    }
}
