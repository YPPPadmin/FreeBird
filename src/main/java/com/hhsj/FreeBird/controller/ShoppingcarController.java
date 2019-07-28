package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.Shoppingcar;
import com.hhsj.FreeBird.pojo.cartCustom;
import com.hhsj.FreeBird.service.ShoppingcarService;
import com.hhsj.FreeBird.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingcarController {

    @Resource
    private ShoppingcarService shoppingcarService;


    @RequestMapping("/addShop")
    public String addShop(@RequestParam(value = "userId") Integer userId,
                          @RequestParam(value = "goodId") Integer goodId, HttpSession session) {
        Shoppingcar car = new Shoppingcar();
        car.setUid(userId);
        car.setGid(goodId);
        car.setModify(new Date());
        car.setState(1);
        int i = shoppingcarService.addShop(car);
        if (i == 1) {
            int num = shoppingcarService.findShop(car.getUid());
            session.setAttribute("count", num);
        }
        return "forward:/goodInfo/" + goodId;
    }


    @RequestMapping(value = "/findShopList")
    public String findShopList(@RequestParam(value = "userId") Integer userId,
                               @RequestParam(defaultValue = "1") Integer pageNum,
                               @RequestParam(defaultValue = "5") Integer pageSize, Model model) {
        List<cartCustom> shopList = shoppingcarService.findShopList(userId, pageNum, pageSize);
        int count = shoppingcarService.findCount(userId);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageSize(pageSize);
        pageUtil.setTotalCount(count);
        pageUtil.setPageNum(pageNum);
        int num = shopList.size();
        model.addAttribute("num", num);
        model.addAttribute("shopList", shopList);
        model.addAttribute("pageUtil", pageUtil);
        return "cart";
    }

    @RequestMapping(value = "/delShop")
    public String delShop(@RequestParam(value = "id", defaultValue = "0") Integer id,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "5") Integer pageSize,
                          @RequestParam(value = "userId") Integer userId,
                          Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("userId", userId);
        int i = shoppingcarService.delShop(map);
        if (i >= 1) {
            List<cartCustom> shopList = shoppingcarService.findShopList(userId, pageNum, pageSize);
            int count = shoppingcarService.findCount(userId);
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageSize(pageSize);
            pageUtil.setTotalCount(count);
            pageUtil.setPageNum(pageNum);
            int num = shopList.size();
            model.addAttribute("num", num);
            model.addAttribute("shopList", shopList);
            model.addAttribute("pageUtil", pageUtil);
            return "cart";
        }
        return "cart";
    }

    @ResponseBody
    @RequestMapping("/addShoppingCar")
    public Object addShoppingCar(Shoppingcar shoppingcar) {
        shoppingcar.setModify(new Date());
        shoppingcar.setState(2);
        int i = shoppingcarService.addShoppingCar(shoppingcar);
        if (i == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}
