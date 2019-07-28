package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.GoodsInfoCustom;
import com.hhsj.FreeBird.pojo.Goodsinfo;
import com.hhsj.FreeBird.pojo.Publishgoods;
import com.hhsj.FreeBird.pojo.Userinfo;
import com.hhsj.FreeBird.service.GoodsInfoService;
import com.hhsj.FreeBird.service.PublishgoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by JacksonYee on 2019/7/22.
 */
@Controller
public class AboutZhiFuController {
    @Resource(name = "publishgoodsService")
    private PublishgoodsService publishgoodsService;

    @Resource
    private GoodsInfoService goodsInfoService;
     //选择支付方式
    @RequestMapping(value="selectType")
    public String selectType(int typeofzhifu, int goodinfoid, HttpSession session, Model model){
        Publishgoods publishgoods=new Publishgoods();
        publishgoods.setState(1);//商家交易中
        publishgoods.setState2(1);//买家未收货
        publishgoods.setGid(goodinfoid);//商品的ID
        Goodsinfo g= goodsInfoService.findGoodInfo(goodinfoid);//更改商品状态
        g.setDisplay(1);
        goodsInfoService.updateDisplay(g);

        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        publishgoods.setUid(userinfo.getId());//买家商品ID
        int insert =publishgoodsService.insertPublishgoods(publishgoods);
        GoodsInfoCustom goodInfo = goodsInfoService.findGoodInfo(goodinfoid);
        model.addAttribute("goodInfo" ,goodInfo);
         if(typeofzhifu==1){
             //线下
             return "Redirect";
         }else{
             //在线
             return "IsJiaoYi";
         }
    }

    //线下
    @RequestMapping(value="/gotoRedirect")
    public String gotoRedirect(){
        return "Redirect";
    }

    @RequestMapping(value="/IsJiaoYi")
    public String gotoIsJiaoYi(){
        return "IsJiaoYi";
    }
    //跳到支付页面
    @RequestMapping(value="/gotozhifu")
    public String gotozhifu( String type, String price, String pay_id) throws IOException {
        /**
         * 接收参数 创建订单
         */
        String token = "ccMKZZBFZfici0iXpu3HMCXOBSwKBryA"; //记得更改 http://codepay.fateqq.com 后台可设置
        String codepay_id ="382961" ;//记得更改 http://codepay.fateqq.com 后台可获得

        String notify_url="127.0.0.1";//通知地址
        String return_url="127.0.0.1";//支付后同步跳转地址
        String param="你好";

        //参数有中文则需要URL编码
        String url="http://codepay.fateqq.com:52888/creat_order?id="+codepay_id+"&pay_id="+pay_id+"&price="+price+"&type="+type+"&token="+token+"&param="+param+"&notify_url="+notify_url+"&return_url="+return_url;

        return "redirect:"+url;
    }
    //返回参数
    @RequestMapping(value="/notify")
    public String notify(HttpServletRequest request){
        String key = "hkg9stV3CmiDakLVmuV0XsDvxWetWyGR"; //记得更改 http://codepay.fateqq.com 后台可设置
        Map<String,String> params = new HashMap<String,String>(); //申明hashMap变量储存接收到的参数名用于排序

        Map requestParams = request.getParameterMap(); //获取请求的全部参数
        String valueStr = ""; //申明字符变量 保存接收到的变量
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            valueStr = values[0];
            //乱码解决，这段代码在出现乱码时使用。如果sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);//增加到params保存
        }
        List<String> keys = new ArrayList<String>(params.keySet()); //转为数组
        Collections.sort(keys); //重新排序
        String prestr = "";
        String sign= params.get("sign"); //获取接收到的sign 参数

        for (int i = 0; i < keys.size(); i++) { //遍历拼接url 拼接成a=1&b=2 进行MD5签名
            String key_name = keys.get(i);
            String value = params.get(key_name);
            if(value== null || value.equals("") ||key_name.equals("sign")){ //跳过这些 不签名
                continue;
            }
            if (prestr.equals("")){
                prestr =  key_name + "=" + value;
            }else{
                prestr =  prestr +"&" + key_name + "=" + value;
            }
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update((prestr+key).getBytes());
        String  mySign = new BigInteger(1, md.digest()).toString(16).toLowerCase();
        if(mySign.length()!=32)mySign="0"+mySign;
        if(mySign.equals(sign)){
            //编码要匹配 编码不一致中文会导致加密结果不一致
            //参数合法处理业务
            //request.getParameter("pay_no") 流水号
            //request.getParameter("pay_id") 用户唯一标识
            //request.getParameter("money") 付款金额
            //request.getParameter("price") 提交的金额
            System.out.println("ok");
        }else{
            //参数不合法
            System.out.println("fail");
        }
        return "";
    }

}
