package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.*;
import com.hhsj.FreeBird.service.GoodsInfoService;
import com.hhsj.FreeBird.service.ShoppingcarService;
import com.hhsj.FreeBird.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by XiaoDu on 2019/7/6.
 */
@Controller
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private GoodsInfoService service;

    @Resource
    private ShoppingcarService shoppingcarService;

    /**
     * findGoodsByModify：根据日期查询最新上架商品
     * findGoodsByView：根据浏览次数查询热销商品
     *
     * @param
     * @return Goodsinfo
     */
    @RequestMapping(value = "/index")
    public String show(Model model) {
        List<Goodsinfo> newGoods = service.findGoodsByModify();
        List<Goodsinfo> goodsinfos = service.findGoodsByView();
        List<Goodsinfo> list = service.findGoodsByGameType();
        List<Goodsinfo> goods = service.findGoodsByType();
        model.addAttribute("newGoods", newGoods);
        model.addAttribute("goods", goodsinfos);
        model.addAttribute("goodList", goods);
        model.addAttribute("list", list);
        return "index";
    }

    /**
     * 用户登录功能
     *
     * @param session       存入一个用户对象
     * @param userinfoCutom
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/findUserinfoLogin")
    public String findUserinfoLogin(HttpSession session, UserinfoCutom userinfoCutom, HttpServletRequest request, Model model) {
        UserinfoCutom userinfoLogin = userInfoService.findUserinfoLogin(userinfoCutom);
        if (userinfoLogin != null) {
            int i = shoppingcarService.findShop(userinfoLogin.getId());
            session.setAttribute("userinfoLogins", userinfoLogin);
            session.setAttribute("num", i);
            return "redirect:/index";
        } else {
            int n = 4;
            String string = "a2b3cd4ef5ghij6kl7mnop8qrst1uvwxy9z";//保存数字0-9 和 大小写字母
            char[] ch = new char[n]; //声明一个字符数组对象ch 保存 验证码
            for (int i = 0; i < n; i++) {
                Random random = new Random();//创建一个新的随机数生成器
                int index = random.nextInt(string.length());//返回[0,string.length)范围的int值    作用：保存下标
                ch[i] = string.charAt(index);//charAt() : 返回指定索引处的 char 值   ==》保存到字符数组对象ch里面
            }
            //将char数组类型转换为String类型保存到result
            //String result = new String(ch);//方法一：直接使用构造方法      String(char[] value) ：分配一个新的 String，使其表示字符数组参数中当前包含的字符序列。
            String result = String.valueOf(ch);//方法二： String方法   valueOf(char c) ：返回 char 参数的字符串表示形式。
            model.addAttribute("yzm", result);
            request.setAttribute("dlsb", "账号或密码错误请重新登录！");
            return "login";
        }
    }

    /**
     * 判断用户输入的QQ邮箱是否注册过
     *
     * @param qq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findUserInfoqq")
    public int findUserInfoqq(String qq) {
        int count = userInfoService.findUserInfoqq(qq);
        return count;
    }

    @ResponseBody
    @RequestMapping(value = "/findUserInfoPhone")
    public int findUserInfoPhone(Userinfo userinfo) {
        int userInfoPhone = userInfoService.findUserInfoPhone(userinfo);
        return userInfoPhone;
    }

    /**
     * 注册用户和密码
     *
     * @param userinfoCutom
     * @param password
     * @param qq
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/insertUserInfoAdd")
    public Object insertUserInfoAdd(Userinfo userinfoCutom, String password, String qq) {
        Userpwd userpwd = new Userpwd();
        userpwd.setPassword(password);//用户的密码
        Date date = new Date();//当前日期
        userpwd.setModify(date);//创建时间
        userinfoCutom.setCreatTime(date);//创建时间
        userinfoCutom.setDateTime(date);
        userinfoCutom.setModify(date);
        String[] s = {"/images/photo.jpg", "photo1.jpg", "photo2.jpg", "photo3.jpg", "photo4.jpg", "photo5.jpg", "1.jpg"
                , "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg", "9.jpg", "10.jpg", "11.jpg", "12.jpg", "13.jpg", "14.jpg", "15.jpg", "16.jpg", "17.jpg"
                , "18.jpg", "19.jpg", "20.jpg", "21.jpg", "22.jpg", "23.jpg", "24.jpg", "25.jpg", "26.jpg", "27.jpg", "28.jpg", "29.jpg", "30.jpg"
        };
        Random rand = new Random();
        int num = rand.nextInt(35);
        userinfoCutom.setAvatar(s[num]);//用户头像默认随机给一个
        userinfoCutom.setStatus(0);//用户的状态，默认正常
        int i1 = userInfoService.insertUserInfoAdd(userinfoCutom);
        int i = 0;
        if (i1 == 1) {
            Userinfo userinfo = userInfoService.findUserInfoUid(qq);
            Integer id = userinfo.getId();
            userpwd.setUid(id);
            i = userInfoService.insertUserInfoPwd(userpwd);
            if (i == 1) {
                return i;
            } else {
                return i;
            }
        }
        return i;
    }

    @RequestMapping(value = "/goToUserCenter")
    public String goToUserCenter(Model m, HttpSession session) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        m.addAttribute("user", userinfo);
        session.setAttribute("user", userinfo);
        return "userinfo";
    }

    //显示用户主页信息
    @RequestMapping(value = "/UserIndex")
    public String findGoodsUserInfo(Integer id, Model model, GoodsInfoCustom goodsInfoCustom, Integer fy) {
        Userinfo userInfoId = userInfoService.findUserInfoId(id);
        goodsInfoCustom.setUid(id);
        List<GoodsInfoCustom> goodsInfoId = userInfoService.findGoodsInfoId(goodsInfoCustom);
        for (int i = 0; i < goodsInfoId.size(); ) {
            int gid = goodsInfoId.get(i).getId();
            int goodsMessageGid = userInfoService.findGoodsMessageGid(gid);
            goodsInfoId.get(i).setCount2(goodsMessageGid);
            i++;
        }
        //为你推荐商品
        int[] s = {2, 12, 18, 26};
        Random rand = new Random();
        int num = rand.nextInt(3);
        fy = s[num];
        List<GoodsInfoCustom> goodsInfoName1 = service.findGoodsInfoTradingValue(fy);
        model.addAttribute("goodsInfoNames1", goodsInfoName1);
        model.addAttribute("id", id);
        model.addAttribute("userInfoIds", userInfoId);
        model.addAttribute("goodsInfoIds", goodsInfoId);
        model.addAttribute("tradingValue", goodsInfoCustom.getTradingValue());
        model.addAttribute("csjg", goodsInfoCustom.getCsjg());
        model.addAttribute("now", new Date());
        return "UserIndex";
    }

    @ResponseBody
    @RequestMapping(value = "/findUserInfoDateTime2")
    public Object findUserInfoDateTime(UserinfoCutom userinfoCutom, HttpSession session) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        Date date = new Date();
        userinfoCutom.setDateTime2(date);
        userinfoCutom.setId(userinfo.getId());
        UserinfoCutom userInfoDateTime = userInfoService.findUserInfoDateTime(userinfoCutom);
        if (userInfoDateTime == null) {
            return 1;
        } else {
            return userInfoDateTime.getDateTime();
        }
    }

}
