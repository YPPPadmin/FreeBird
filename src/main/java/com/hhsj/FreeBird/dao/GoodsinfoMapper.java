package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.GoodsInfoCustom;
import com.hhsj.FreeBird.pojo.Goodsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsinfoMapper {
    /**
     * 按条件查询二手商品并且进行分页
     *
     * @param
     * @return
     */
    List<GoodsInfoCustom> findGoodsInfoName(GoodsInfoCustom goodsInfoCutom);

    /**
     * 查看商品区间的价格并且进行分页
     *
     * @param
     * @return
     */
    List<GoodsInfoCustom> findGoodsInfoTradingValue(int fy);

    List<Goodsinfo> findGoodsByType();

    /*以下是关于首页查询商品相关代码*/

    /**
     * 根据日期查询最新上架商品
     *
     * @param
     * @return Goodsinfo
     */
    List<Goodsinfo> findGoodsByModify();

    /**
     * 根据浏览次数查询热销商品
     *
     * @param
     * @return Goodsinfo
     */
    List<Goodsinfo> findGoodsByView();

    /**
     * 根据商品名称模糊查询
     *
     * @param
     * @return Goodsinfo
     */
    List<Goodsinfo> findGoodsByName(@Param("name") String name);

    GoodsInfoCustom findGoodInfo(Integer id);


    /**
     * 根据游戏设备查询商品
     *
     * @param
     * @return Goodsinfo
     */
    List<Goodsinfo> findGoodsByGameType();

    /**
     * 修改浏览次数
     */
    public int updateView(GoodsInfoCustom goodsInfoCustom);

    /**
     * 发布商品
     */
    public int addGoodInfo(GoodsInfoCustom goodsInfoCustom);

    //更改商品状态
    int updateDisplay(Goodsinfo g);
}