package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.WantgoodsMapper;
import com.hhsj.FreeBird.pojo.WantGoodsCutom;
import com.hhsj.FreeBird.pojo.Wantgoods;
import com.hhsj.FreeBird.pojo.Wantgoodsmessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiaoDu on 2019/7/12.
 */
@Service
public class WantGoodsService {
    @Resource
    private WantgoodsMapper wantgoodsMapper;

    /**
     * 发布求购商品
     *
     * @param wantgoods
     * @return
     */
    public int addWantGoods(Wantgoods wantgoods) {
        int i = wantgoodsMapper.addWantGoods(wantgoods);
        return i;
    }

    /**
     * 按条件查询所有用户发布的求购信息
     *
     * @param wantgoods
     * @return
     */
    public List<WantGoodsCutom> findWantGoodsAll(WantGoodsCutom wantgoods) {
        List<WantGoodsCutom> wantGoodsAll = wantgoodsMapper.findWantGoodsAll(wantgoods);
        return wantGoodsAll;
    }

    /**
     * 查询用户发布求购信息的条数
     *
     * @param uid
     * @return
     */
    public int findWantGoodsUid(int uid) {
        int wantGoodsUid = wantgoodsMapper.findWantGoodsUid(uid);
        return wantGoodsUid;
    }

    /**
     * 查询用户自己发布的求购信息
     *
     * @param wantgoods
     * @return
     */
    public List<Wantgoods> findWantGoodsUser(Wantgoods wantgoods) {
        List<Wantgoods> wantGoodsUser = wantgoodsMapper.findWantGoodsUser(wantgoods);
        return wantGoodsUser;
    }

    /**
     * 删除求购信息
     *
     * @return
     */
    public int deleteMywantgood(int wid) {
        int i = wantgoodsMapper.deleteMywantgood(wid);
        return i;
    }

    public int deleteWantgoodsMessage(int wid) {
        int i2 = wantgoodsMapper.deleteWantgoodsMessage(wid);
        return i2;
    }

    public int deleteWantGoods(int id) {
        int i2 = wantgoodsMapper.deleteWantGoods(id);
        return i2;
    }

    /**
     * 查询出用户要留言的用户名
     *
     * @param id
     * @return
     */
    public WantGoodsCutom findWantGoodsId(Integer id) {
        WantGoodsCutom wantGoodsId = wantgoodsMapper.findWantGoodsId(id);
        return wantGoodsId;
    }

    /**
     * 查询出用户要留言的用户名
     *
     * @param
     * @return
     */
    public int AddWantGoodsMessage(Wantgoodsmessage wantGoodsCutom) {
        int i = wantgoodsMapper.AddWantGoodsMessage(wantGoodsCutom);
        return i;
    }

    /**
     * 查看用户的留言信息
     *
     * @param id
     * @return
     */
    public List<WantGoodsCutom> findWantGoodsId2(Integer id) {
        List<WantGoodsCutom> wantGoodsId2 = wantgoodsMapper.findWantGoodsId2(id);
        return wantGoodsId2;
    }
}
