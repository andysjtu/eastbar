/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.SiteLiveDataService;
import org.eastbar.web.ipc.biz.SiteLiveDataBO;
import org.eastbar.web.ipc.dao.SiteLiveDataDao;
import org.eastbar.web.ipc.entity.SiteLiveData;
import org.eastbar.web.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:47
 * @description :
 */
@Service
@Transactional
public class SiteLiveDataServiceImpl implements SiteLiveDataService {

    public static Map<String,SiteLiveData> LIVELATEST = new HashMap<>();

    @Autowired
    private SiteLiveDataDao siteLiveDataDao;

    @Override
    public SiteLiveData get(String siteCode) {
        return siteLiveDataDao.get(siteCode);
    }

    @Override
    public Map<String,SiteLiveData> getAllLatest() {
        List<SiteLiveData> ml = siteLiveDataDao.getAllLatest();

        for(SiteLiveData m:ml){
            LIVELATEST.put(m.getSiteCode(),m);
        }
        return LIVELATEST;
    }

    public SiteLiveData getLiveCache(String siteCode){
        if(!LIVELATEST.isEmpty()){
            return LIVELATEST.get(siteCode);
        }
        return null;
    }

    @Override
    public PageInfo getAll(SiteLiveDataBO siteLiveDataBO) {
        try{
            PageHelper.startPage(siteLiveDataBO.getPage(), siteLiveDataBO.getRows());
            Map<String,Object> re= Bean2Map.trans(siteLiveDataBO);
            List<SiteLiveData> list=siteLiveDataDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
