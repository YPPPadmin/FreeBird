package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.GoodsmessageMapper;
import com.hhsj.FreeBird.pojo.GoodMessageCustom;
import com.hhsj.FreeBird.pojo.Goodsmessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administered on 2019/7/11.
 */
@Service
public class GoodsMessageService {

    @Resource
    public GoodsmessageMapper goodsmessageMapper;

    public List<GoodMessageCustom> findGoodsmessage(Integer id) {
        List<GoodMessageCustom> goodsmessages = goodsmessageMapper.findGoodsmessage(id);
        return goodsmessages;
    }

    public int addGoodsmessage(Goodsmessage goodsmessage) {
        int i = goodsmessageMapper.addGoodsmessage(goodsmessage);
        return i;
    }
}
