package com.hhsj.FreeBird.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * Created by XiaoDu on 2019/6/26.
 */
@Controller
public class controller {


    //登录给出验证码
    @RequestMapping(value = "/login")
    public String show2(Model model) {
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
        return "Login";
    }

    //跳转注册页面
    @RequestMapping(value = "/Regedit")
    public String show3(Model model) {
        return "Regedit";
    }

    //跳转查询商品页面
    @RequestMapping(value = "/findName")
    public String show3() {
        return "findName";
    }

    //跳转发布求购商品页面
    @RequestMapping(value = "/addProvider")
    public String show4() {
        return "AddProvider";
    }

    //跳转忘记密码页面
    @RequestMapping(value = "/findpass")
    public String show5() {
        return "findpass";
    }

    //退出登录
    @RequestMapping(value = "/login2")
    public String show6(HttpSession session,Model model) {
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
    }
    @RequestMapping(value = "/ListProvider")
    public String show7(){
        return "ListProvider";
    }

}
