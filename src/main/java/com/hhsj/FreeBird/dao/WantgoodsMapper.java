package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.WantGoodsCutom;
import com.hhsj.FreeBird.pojo.Wantgoods;
import com.hhsj.FreeBird.pojo.Wantgoodsmessage;

import java.util.List;

public interface WantgoodsMapper {

    /**
     * 发布求购商品
     *
     * @param wantgoods
     * @return
     */
    int addWantGoods(Wantgoods wantgoods);

    /**
     * 按条件查询所有用户发布的求购信息
     *
     * @param wantgoods
     * @return
     */
    List<WantGoodsCutom> findWantGoodsAll(WantGoodsCutom wantgoods);

    /**
     * 查询用户发布求购信息的条数
     *
     * @param uid
     * @return
     */
    int findWantGoodsUid(int uid);

    /**
     * 查询用户自己发布的求购信息
     *
     * @param wantgoods
     * @return
     */
    List<Wantgoods> findWantGoodsUser(Wantgoods wantgoods);

    /**
     * 删除求购信息
     */
    int deleteWantgoodsMessage(int wid);

    int deleteMywantgood(int wid);

    int deleteWantGoods(int id);

    /**
     * 查询出用户要留言的用户名
     *
     * @param id
     * @return
     */
    WantGoodsCutom findWantGoodsId(Integer id);

    /**
     * 给发布求购信息的用户留言
     *
     * @param wantGoodsCutom
     * @return
     */
    int AddWantGoodsMessage(Wantgoodsmessage wantGoodsCutom);

    /**
     * 查看用户的留言信息
     *
     * @param id
     * @return
     */
    List<WantGoodsCutom> findWantGoodsId2(Integer id);
}