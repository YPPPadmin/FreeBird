package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.PublishgoodsMapper;
import com.hhsj.FreeBird.pojo.Publishgoods;
import com.hhsj.FreeBird.pojo.PublishgoodsCustom;
import com.hhsj.FreeBird.pojo.PublishgoodsCustoms;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 12789 on 2019/7/13.
 */
@Service(value = "publishgoodsService")
public class PublishgoodsService {
    @Resource(name = "publishgoodsMapper")
    private PublishgoodsMapper publishgoodsMapper;

    public List<PublishgoodsCustom> findPublishgoodsList(Integer uid) throws Exception {
        List<PublishgoodsCustom> publishgoodsList = publishgoodsMapper.findPublishgoodsList(uid);
        return publishgoodsList;
    }

    public List<PublishgoodsCustom> findPublishList(Integer uid, Integer state) throws Exception {
        List<PublishgoodsCustom> publishgoodsList = publishgoodsMapper.findPublishList(uid, state);
        return publishgoodsList;
    }

    public PublishgoodsCustom findPublishgoods(Integer uid, Integer gid) throws Exception {
        PublishgoodsCustom publishgoods = publishgoodsMapper.findPublishgoods(uid, gid);
        return publishgoods;
    }

    public int deletePublishgoods(Integer gid) throws Exception {
        int i = publishgoodsMapper.deletePublishgoods(gid);
        return i;
    }

    public int deletebuiedgoods(Integer gid) throws Exception {
        int i = publishgoodsMapper.deletebuiedgoods(gid);
        return i;
    }

    public int deletegoodscollection(Integer gid) throws Exception {
        int i = publishgoodsMapper.deletegoodscollection(gid);
        return i;
    }

    public int deletegoodsinfo(Integer gid) throws Exception {
        int i = publishgoodsMapper.deletegoodsinfo(gid);
        return i;
    }

    public int deletegoodsmessage(Integer gid) throws Exception {
        int i = publishgoodsMapper.deletegoodsmessage(gid);
        return i;
    }

    public int deleteshoppingcar(Integer gid) throws Exception {
        int i = publishgoodsMapper.deleteshoppingcar(gid);
        return i;
    }
    public int deleteBowers(Integer gid) throws Exception {
       int i=publishgoodsMapper.deleteBowser(gid);
        return i;
    }
    public int insertPublishgoods(Publishgoods publishgoods){
        return publishgoodsMapper.insertPublishgoods(publishgoods);
    }
    public  List<PublishgoodsCustoms> findSBCX(int id){
        return  publishgoodsMapper.findSBCX(id);
    }
    public  List<PublishgoodsCustoms> findSBCX2(int id){
        return  publishgoodsMapper.findSBCX2(id);
    }
    public  int updateState2(Publishgoods publishgoods){return publishgoodsMapper.updateState2(publishgoods);}
    public  int updateState(Publishgoods publishgoods){return publishgoodsMapper.updateState(publishgoods);}
    //    卖家取消交易
    @Resource
    GoodsInfoService goodsInfoService;
    public int quxiaojiaoyi(int id){
        return publishgoodsMapper.quxiaojiaoyi(id);
    }
}
