package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.GoodsinfoMapper;
import com.hhsj.FreeBird.pojo.GoodsInfoCustom;
import com.hhsj.FreeBird.pojo.Goodsinfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiaoDu on 2019/7/9.
 */
@Service
public class GoodsInfoService {
    @Resource
    private GoodsinfoMapper goodsinfoMapper;

    /**
     * 按条件查询二手商品并且进行分页
     *
     * @param
     * @return
     */
    public List<GoodsInfoCustom> findGoodsInfoName(GoodsInfoCustom goodsInfoCutom) {
        List<GoodsInfoCustom> goodsInfoName = goodsinfoMapper.findGoodsInfoName(goodsInfoCutom);
        return goodsInfoName;
    }

    /**
     * 查看商品区间的价格并且进行分页
     *
     * @param
     * @return
     */
    public List<GoodsInfoCustom> findGoodsInfoTradingValue(int fy) {
        List<GoodsInfoCustom> goodsInfoName = goodsinfoMapper.findGoodsInfoTradingValue(fy);
        return goodsInfoName;
    }

    /*以下是查询商品首页相关的代码*/

    /**
     * 根据日期查询最新上架商品
     *
     * @param
     * @return Goodsinfo
     */
    public List<Goodsinfo> findGoodsByModify() {
        return goodsinfoMapper.findGoodsByModify();
    }

    public List<Goodsinfo> findGoodsByType() {
        return goodsinfoMapper.findGoodsByType();
    }


    /**
     * 根据浏览次数查询热销商品
     *
     * @param
     * @return Goodsinfo
     */
    public List<Goodsinfo> findGoodsByView() {
        return goodsinfoMapper.findGoodsByView();
    }


    /**
     * 根据商品名称模糊查询
     *
     * @param
     * @return Goodsinfo
     */
    public List<Goodsinfo> findGoodsByName(String name) {
        return goodsinfoMapper.findGoodsByName(name);
    }


    public GoodsInfoCustom findGoodInfo(Integer id){
        GoodsInfoCustom goodInfo = goodsinfoMapper.findGoodInfo(id);
        return goodInfo;
    }

    /**
     * 根据游戏设备查询商品
     *
     * @param
     * @return Goodsinfo
     */
    public List<Goodsinfo> findGoodsByGameType(){
          return goodsinfoMapper.findGoodsByGameType();
    }

    /**
     * 修改浏览次数
     */
    public int updateView(GoodsInfoCustom goodsInfoCustom) {
        int i = goodsinfoMapper.updateView(goodsInfoCustom);
        return i;
    }

    /**
     * 发布商品
     */
    public int addGoodInfo(GoodsInfoCustom goodsInfoCustom) {
        int i = goodsinfoMapper.addGoodInfo(goodsInfoCustom);
        return i;
    }

//    更改商品状态
       public int updateDisplay(Goodsinfo g){return  goodsinfoMapper.updateDisplay(g);}
}
