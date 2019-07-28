package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Publishgoods;
import com.hhsj.FreeBird.pojo.PublishgoodsCustom;
import com.hhsj.FreeBird.pojo.PublishgoodsCustoms;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "publishgoodsMapper")
public interface PublishgoodsMapper {
    List<PublishgoodsCustom> findPublishgoodsList(@Param(value = "uid") Integer uid) throws Exception;//查看我发布的商品

    PublishgoodsCustom findPublishgoods(@Param(value = "uid") Integer uid, @Param(value = "gid") Integer gid) throws Exception;//查看我发布的商品详情

    int deletePublishgoods(@Param(value = "gid") Integer gid) throws Exception;//删除发布的商品

    int deletegoodscollection(@Param(value = "gid") Integer gid) throws Exception;//删除发布商品的收藏;

    int deletebuiedgoods(@Param(value = "gid") Integer gid) throws Exception;//删除发布商品购买的

    int deletegoodsmessage(@Param(value = "gid") Integer gid) throws Exception;//删除发布商品留言

    int deleteshoppingcar(@Param(value = "gid") Integer gid) throws Exception;//删除发布商品的购物车

    int deletegoodsinfo(@Param(value = "gid") Integer gid) throws Exception;//删除商品
    int deleteBowser(@Param(value = "gid") Integer gid) throws Exception;//删除浏览商品

    List<PublishgoodsCustom> findPublishList(@Param(value = "uid") Integer uid, @Param(value = "display") Integer display) throws Exception;//根据条件查看我发布的商品

    int insertPublishgoods(Publishgoods publishgoods);
    List<PublishgoodsCustoms> findSBCX(int id);
    List<PublishgoodsCustoms> findSBCX2(int id);
    int updateState2(Publishgoods publishgoods);
    int updateState(Publishgoods publishgoods);

    //    卖家取消交易
    int quxiaojiaoyi(int id);

}