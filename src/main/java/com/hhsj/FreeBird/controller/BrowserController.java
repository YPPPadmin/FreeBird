package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.BrowserCustom;
import com.hhsj.FreeBird.service.BrowserService;
import com.hhsj.FreeBird.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BrowserController {

    @Resource
    private BrowserService service;


    @RequestMapping(value = "/findBrowser")
    public String findBrowser(Model model,
                              @RequestParam("userId") Integer userId,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize) {
        List<BrowserCustom> browsers = service.findBrowser(userId, pageNum, pageSize);
        int count = service.findCount(userId);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageSize(pageSize);
        pageUtil.setTotalCount(count);
        pageUtil.setPageNum(pageNum);
        int num = browsers.size();
        model.addAttribute("num", num);
        model.addAttribute("pageUtil", pageUtil);
        model.addAttribute("browsers", browsers);
        return "Browser";
    }

    //删除浏览记录
    @RequestMapping("/delBrowser")
    public String delBrowser(Model model,
                             @RequestParam(value = "userId") Integer userId,
                             @RequestParam(value = "id", defaultValue = "0") Integer id,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("id", id);
        int i = service.delBrowser(map);
        if (i >= 1) {
            List<BrowserCustom> browsers = service.findBrowser(userId, pageNum, pageSize);
            int count = service.findCount(userId);
            PageUtil pageUtil = new PageUtil();
            pageUtil.setPageSize(pageSize);
            pageUtil.setTotalCount(count);
            pageUtil.setPageNum(pageNum);
            int num = browsers.size();
            model.addAttribute("num", num);
            model.addAttribute("pageUtil", pageUtil);
            model.addAttribute("browsers", browsers);
            return "Browser";
        }
        return "Browser";
    }
}
