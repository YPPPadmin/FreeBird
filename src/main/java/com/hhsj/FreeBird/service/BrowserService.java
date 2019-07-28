package com.hhsj.FreeBird.service;

import com.hhsj.FreeBird.dao.BrowserMapper;
import com.hhsj.FreeBird.pojo.Browser;
import com.hhsj.FreeBird.pojo.BrowserCustom;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrowserService {

    @Resource
    private BrowserMapper browserMapper;

    /**
     * 添加浏览记录
     * */
    public int addBrowser(Browser browser){
        return browserMapper.addBrowser(browser);
    }

    /**
     * 查询登录用户的浏览记录
     */
    public List<BrowserCustom> findBrowser(int uId, Integer pageNum, Integer pageSize) {
        Integer pageIndex = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        map.put("uId", uId);
        map.put("pageIndex", pageIndex);
        map.put("pageSize", pageSize);
        return browserMapper.findBrowser(map);
    }


    //删除浏览记录
    public int delBrowser(Map<String,Object> map){
        return browserMapper.delBrowser(map);
    }


    public int findCount(Integer userId){
        return browserMapper.findCount(userId);
    }
}
