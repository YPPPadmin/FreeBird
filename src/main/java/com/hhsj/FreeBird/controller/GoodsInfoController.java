package com.hhsj.FreeBird.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhsj.FreeBird.pojo.*;
import com.hhsj.FreeBird.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by XiaoDu on 2019/7/9.
 */
@Controller
public class GoodsInfoController {
    @Resource
    private GoodsInfoService service;

    @Resource
    private GoodsMessageService messageService;

    @Resource
    private BrowserService browserService;

    @Resource
    private GoodsTypeService typeService;


    @Resource
    private ShoppingcarService shoppingcarService;

    /**
     * 按条件查询二手商品并且进行分页
     *
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/findGoodsInfoName")
    public String findGoodsInfoName(Model model, GoodsInfoCustom goodsInfoCutom, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, Integer fy) {
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 12);
        List<GoodsInfoCustom> goodsInfoName = service.findGoodsInfoName(goodsInfoCutom);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<GoodsInfoCustom> pageInfo = new PageInfo<GoodsInfoCustom>(goodsInfoName);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("goodsInfoNames", goodsInfoName);//存储商品信息
        model.addAttribute("name", goodsInfoCutom.getName());//存储查询的条件
        model.addAttribute("tradingValue", goodsInfoCutom.getTradingValue());
        model.addAttribute("csjg", goodsInfoCutom.getCsjg());
        //为你推荐商品
        int[] s = {5, 10, 20, 25};
        Random rand = new Random();
        int num = rand.nextInt(3);
        fy = s[num];
        List<GoodsInfoCustom> goodsInfoName1 = service.findGoodsInfoTradingValue(fy);
        model.addAttribute("goodsInfoNames1", goodsInfoName1);
        return "findName";
    }

    /**
     * 查看商品区间的价格并且进行分页
     *
     * @param
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/GoodsInfoTradingValue")
    public String GoodsInfoTradingValue(Model model) {
        return "findName";
    }


    @RequestMapping(value = "/findGoodsByName")
    public String findGoodsByName(Model model, String name, String brand) {
        List<Goodsinfo> list = service.findGoodsByName(name);
        model.addAttribute("list", list);
        model.addAttribute("brand", brand);
        return "List";
    }


    /**
     * 根据商品id查询商品的详情信息
     *
     * @param model
     * @param id
     * @param uId
     * @return
     */
    @RequestMapping(value = "/goodInfo/{id}")
    public String goodsInfo(Model model,
                            @PathVariable("id") Integer id, @RequestParam(defaultValue = "0") Integer uId, @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "5") Integer pageSize) {
        if (uId != null && uId != 0) {
            //添加浏览记录
            Browser browser = new Browser();
            browser.setGoodId(id);
            browser.setUserId(uId);
            browser.setBornDate(new Date());
            browserService.addBrowser(browser);
            Shoppingcar shop1 = shoppingcarService.findShop1(uId,id);
            model.addAttribute("shop", shop1);
        }
        //查看商品详情
        GoodsInfoCustom goodInfo = service.findGoodInfo(id);
        List<GoodMessageCustom> goodsmessage = messageService.findGoodsmessage(id);
        //修改浏览次数
        GoodsInfoCustom goodsInfoCustom = new GoodsInfoCustom();
        goodsInfoCustom.setId(goodInfo.getId());
        goodsInfoCustom.setView(goodInfo.getView() + 1);
        int i = service.updateView(goodsInfoCustom);
        if (i == 1) {
            goodInfo = service.findGoodInfo(id);
        }
        model.addAttribute("count", goodsmessage.size());
        model.addAttribute("goodsmessage", goodsmessage);
        model.addAttribute("goodInfo", goodInfo);
        return "Show";
    }


    /**
     * 跳转发布商品页面
     *
     * @param m
     * @param session
     * @return
     */
    @RequestMapping("/pubGoods")
    public String pubGoods(Model m, HttpSession session) {
        Userinfo userinfo = (Userinfo) session.getAttribute("userinfoLogins");
        List<Goodstype> goodTypeList = typeService.findGoodTypeList();
        m.addAttribute("user", userinfo);
        m.addAttribute("goodTypeList", goodTypeList);
        return "pubGoods";
    }


    /**
     * 提交发布的商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoodsSubmit")
    public String publishGoodsSubmit(HttpServletRequest request, HttpSession session, GoodsInfoCustom
            goods, @RequestParam("myfile") MultipartFile[] file)
            throws Exception {
        // 查询出当前用户cur_user对象，便于使用id
        Userinfo cur_user = (Userinfo) request.getSession().getAttribute("userinfoLogins");
        goods.setUid(cur_user.getId());
        goods.setModify(new Date());
        goods.setShelfTime(new Date());
        goods.setDisplay(2);
        goods.setView(1);
        List<String> fileName = new ArrayList<>();
        if (file != null && file.length > 0) {
            //组合image名称，“;隔开”
            PrintWriter out = null;
            for (int i = 0; i < file.length; i++) {
                if (!file[i].isEmpty()) {
                    //上传文件，随机名称，";"分号隔开
                    fileName.add(com.hhsj.FreeBird.util.FileUtil.uploadImage(request, "/attached/", file[i], true));
                }
            }
        }
        for (int k = 0; k < fileName.size(); k++) {
            if (k == 0) {
                goods.setImage("../attached/" + fileName.get(0));
            }
            if (k == 1) {
                goods.setImage1("../attached/" + fileName.get(1));
            }
            if (k == 2) {
                goods.setImage2("../attached/" + fileName.get(2));
            }
            if (k == 3) {
                goods.setImage3("../attached/" + fileName.get(3));
            }
        }
        service.addGoodInfo(goods);// 在goods表中插入物品
        return "redirect:/index";
    }


}