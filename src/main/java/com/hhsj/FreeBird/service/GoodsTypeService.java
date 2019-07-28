package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.GoodstypeMapper;
import com.hhsj.FreeBird.pojo.Goodsinfo;
import com.hhsj.FreeBird.pojo.Goodstype;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsTypeService {

    @Resource
    private GoodstypeMapper goodstypeMapper;

    public List<Goodsinfo> findGoodByTypeList(Integer pageNum, Integer pageSize, Integer type, String name) {
        Integer pageIndex = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("name", name);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        List<Goodsinfo> list = goodstypeMapper.findGoodByTypeList(map);
        return list;
    }

    public int findCountByType(Integer type,String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("name", name);
        return goodstypeMapper.findCountByType(map);
    }

    /**
     * 查询所有类别
     *
     * @return
     */
    public List<Goodstype> findGoodTypeList() {
        List<Goodstype> goodTypeList = goodstypeMapper.findGoodTypeList();
        return goodTypeList;
    }
}
