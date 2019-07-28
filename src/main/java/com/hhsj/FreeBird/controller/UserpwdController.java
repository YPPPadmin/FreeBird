package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.pojo.Userpwd;
import com.hhsj.FreeBird.service.UserpwdService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by 12789 on 2019/7/6.
 */
@Controller
public class UserpwdController {
    @Resource
    private UserpwdService userpwdService;

    Integer id;//通过绑定的QQ找到对应的用户id来找回密码

    /*
        查询用户绑定的QQ邮箱
     */
    @RequestMapping(value = "/findpass2")
    public String findpass2(Userinfo userinfo, Model model) {
        Userinfo findqq = userpwdService.findPhone(userinfo);
        if (findqq != null) {
            model.addAttribute("QQ", findqq.getQq());
            id = findqq.getId();
            return "findpass2";
        } else {
            return "findpass";
        }
    }

    /*
    查询到数据库手机号是否存在
     */
    @RequestMapping(value = "/findqqName", method = RequestMethod.POST)
    public String findqqName(String phone, Model model) {
        Userinfo userinfo = new Userinfo();
        userinfo.setPhone(phone);
        Userinfo findqq = userpwdService.findqqName(userinfo);
        if (findqq != null) {
            return "forward:/findpass2";
        } else {
            model.addAttribute("sb", "没有查询到该手机号请重新输入!");
            model.addAttribute("phone", phone);
            return "findpass";
        }
    }

    /*
    找回密码成功返回登录失败返回本页
     */
    @RequestMapping(value = "/updatePwd")
    public String updatePwd(Userpwd userpwd, Model model, HttpServletRequest request) {
        Integer id1 = id;//通过绑定的QQ找到对应的用户id
        userpwd.setUid(id1);
        int i1 = userpwdService.updatePwd(userpwd);
        if (i1 == 1) {
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
        } else {
            return "findpass3";
        }
    }

    /**
     * 跳转到修改密码页面
     *
     * @return
     */
    @RequestMapping(value = "/findpass3")
    public String findpass3() {
        return "findpass3";
    }


    /*以下是修改密码查看用户信息相关代码*/
    @RequestMapping("/goToUpdatePwd")
    public String gotoUpdatePwd(HttpSession session, Model model) {
        Userinfo user = (Userinfo) session.getAttribute("userinfoLogins");
        model.addAttribute("user", user);
        //根据ID查询原密码
        int id = user.getId();
        String password = userpwdService.selectPwdById(id);
        model.addAttribute("pwd", password);
        return "updatePwd";
    }

    @RequestMapping("/updatePwd1")
    public String updatePwd1(String newpassword2, HttpSession session, Model model) {
        Userpwd u = new Userpwd();
        Userinfo user = (Userinfo) session.getAttribute("userinfoLogins");

        int id = user.getId();
        u.setId(id);
        u.setPassword(newpassword2);
        int data = userpwdService.updatePwd1(u);
        if (data == 1) {
            //退出登录然后清空session
            session.invalidate();
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
            return "login";
        } else {
            return "goToUpdatePwd";
        }
    }


    @RequestMapping("/testuu")
    public String g5otoUpdatePwd() {
        return "testPay";
    }
}

