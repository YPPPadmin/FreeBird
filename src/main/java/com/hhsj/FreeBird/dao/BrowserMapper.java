package com.hhsj.FreeBird.dao;

import com.hhsj.FreeBird.pojo.Browser;
import com.hhsj.FreeBird.pojo.BrowserCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BrowserMapper {

    /**
     * 添加浏览记录
     */
    int addBrowser(Browser browser);

    /**
     * 查询登录用户的浏览记录
     */
    List<BrowserCustom> findBrowser(Map<String, Object> map);

    //删除浏览记录
    int delBrowser(Map<String, Object> map);


    int findCount(@Param("userId") Integer userId);
}
