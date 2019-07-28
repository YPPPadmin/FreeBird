package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Goodsinfo;
import com.hhsj.FreeBird.pojo.Goodstype;

import java.util.List;
import java.util.Map;

public interface GoodstypeMapper {

    List<Goodsinfo> findGoodByTypeList(Map<String, Object> map);

    int findCountByType(Map<String, Object> map);

    public List<Goodstype> findGoodTypeList();
}