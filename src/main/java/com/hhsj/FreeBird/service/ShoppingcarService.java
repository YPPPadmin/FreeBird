package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.ShoppingcarMapper;
import com.hhsj.FreeBird.pojo.Shoppingcar;
import com.hhsj.FreeBird.pojo.cartCustom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingcarService {

    @Resource
    private ShoppingcarMapper shoppingcarMapper;

    /**
     * 查询登录用户的购物车中商品总数量
     */
    public int findShop(Integer uId) {
        return shoppingcarMapper.findShop(uId);
    }

    /**
     * 向购物车中添加商品
     */
    public int addShop(Shoppingcar car) {
        return shoppingcarMapper.addShop(car);
    }


    /**
     * 查询购物车中的所有商品
     */
    /**
     * 查询购物车中的所有商品
     */
    public List<cartCustom> findShopList(Integer uId, Integer pageNum, Integer pageSize) {
        Integer pageIndex = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("uId", uId);
        map.put("pageSize", pageSize);
        map.put("pageIndex", pageIndex);
        List<cartCustom> list = shoppingcarMapper.findShopList(map);
        return list;
    }


    public int findCount(Integer uId) {
        return shoppingcarMapper.findCount(uId);
    }


    public int delShop(Map<String, Object> map) {
        return shoppingcarMapper.delShop(map);
    }

    /**
     * 添加购物车
     */
    public int addShoppingCar(Shoppingcar shoppingcar) {
        int i = shoppingcarMapper.addShoppingCar(shoppingcar);
        return i;
    }

    public Shoppingcar findShop1(Integer uid,Integer gid){
        Shoppingcar shop = shoppingcarMapper.findShop1(uid,gid);
        return shop;
    }
}
