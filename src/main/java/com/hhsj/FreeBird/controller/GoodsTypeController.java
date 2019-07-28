package com.hhsj.FreeBird.controller;

import com.hhsj.FreeBird.pojo.Goodsinfo;
import com.hhsj.FreeBird.service.GoodsTypeService;
import com.hhsj.FreeBird.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class GoodsTypeController {
    @Resource
    private GoodsTypeService goodsTypeService;

    @RequestMapping(value = "/findGoodByTypeList")
    public String findGoodByTypeList(Model model, Integer type,
                                     @RequestParam(defaultValue = "",required = false) String name,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "8") Integer pageSize) {
        List<Goodsinfo> list = goodsTypeService.findGoodByTypeList(pageNum, pageSize, type,name);
        int count = goodsTypeService.findCountByType(type,name);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setTotalCount(count);
        pageUtil.setPageSize(pageSize);
        pageUtil.setPageNum(pageNum);
        model.addAttribute("type", type);
        model.addAttribute("list", list);
        model.addAttribute("pageUtil", pageUtil);
        return "List";
    }
}
