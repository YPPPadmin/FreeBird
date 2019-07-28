package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.BuiedgoodsCustom;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "buiedgoodsMapper")
public interface BuiedgoodsMapper {
      BuiedgoodsCustom findBuiedgoods(@Param(value = "uid") Integer uid, @Param(value = "gid") Integer gid)throws Exception;//查看我的订单详情
      List<BuiedgoodsCustom> findBuiedgoodsList(@Param(value = "uid") Integer uid, @Param(value = "state") Integer state)throws Exception;//查看我的订单
}