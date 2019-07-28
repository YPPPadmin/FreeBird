package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.BuiedgoodsMapper;
import com.hhsj.FreeBird.pojo.BuiedgoodsCustom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 12789 on 2019/7/12.
 */
@Service(value = "buiedgoodsService")
public class BuiedgoodsService {
    @Resource(name = "buiedgoodsMapper")
    private BuiedgoodsMapper buiedgoodsMapper;
    /*
    根据用户id来查找我的订单还有订单状态实现条件查询
     */
    public List<BuiedgoodsCustom> findBuiedgoodsList(Integer uid, Integer state) throws Exception {
        List<BuiedgoodsCustom> bUiedgoodsList = buiedgoodsMapper.findBuiedgoodsList(uid,state);
        return bUiedgoodsList;
    }
    /*
    根据用户id和商品的id来查找订单详情
     */
    public BuiedgoodsCustom findBuiedgoods(Integer uid, Integer gid) throws Exception {
        BuiedgoodsCustom buiedgoods1 = buiedgoodsMapper.findBuiedgoods(uid,gid);
        return buiedgoods1;
    }
}
