package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.GoodMessageCustom;
import com.hhsj.FreeBird.pojo.Goodsmessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsmessageMapper {

    public List<GoodMessageCustom> findGoodsmessage(@Param("id") Integer id);

    public int addGoodsmessage(Goodsmessage goodsmessage);
}