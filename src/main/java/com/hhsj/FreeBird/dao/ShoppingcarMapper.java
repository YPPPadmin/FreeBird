package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Shoppingcar;
import com.hhsj.FreeBird.pojo.cartCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoppingcarMapper {

    /**
     * 查询登录用户的购物车中商品总数量
     */
    int findShop(@Param("uId") Integer uId);

    /**
     * 向购物车中添加商品
     */
    int addShop(Shoppingcar shop);


    /**
     * 查询购物车中的所有商品
     */
    List<cartCustom> findShopList(Map<String, Object> map);

    int delShop(Map<String, Object> map);

    int findCount(@Param("uId") Integer uId);

    /**
     * 添加购物车
     */
    public int addShoppingCar(Shoppingcar shoppingcar);

    // 根据uid查询购物车中所对应的商品
    public Shoppingcar findShop1(@Param("uid") Integer uid,@Param("gid")int gid);
}