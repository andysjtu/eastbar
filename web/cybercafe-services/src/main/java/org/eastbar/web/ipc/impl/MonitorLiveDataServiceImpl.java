/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.MonitorLiveDataService;
import org.eastbar.web.ipc.biz.MonitorLiveDataBO;
import org.eastbar.web.ipc.dao.MonitorLiveDataDao;
import org.eastbar.web.ipc.entity.MonitorLiveData;
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
 * @time 下午3:39
 * @description :
 */

@Service
@Transactional
public class  MonitorLiveDataServiceImpl implements MonitorLiveDataService {

    public static Map<String,MonitorLiveData> LIVELATEST = new HashMap<>();
    @Autowired
    private MonitorLiveDataDao monitorLiveDataDao;

    @Override
    public MonitorLiveData get(Integer id) {
        return monitorLiveDataDao.get(id);
    }

    @Override
    public Map<String,MonitorLiveData> getAllLatest() {

        List<MonitorLiveData> ml = monitorLiveDataDao.getAllLatest();

        for(MonitorLiveData m:ml){
            LIVELATEST.put(m.getMonitorCode(),m);
        }
        return LIVELATEST;
    }

    @Override
    public List<MonitorLiveData> byMonitorCode(String monitorCode) {
        return monitorLiveDataDao.byMonitorCode(monitorCode);
    }

    @Override
    public PageInfo getAll(MonitorLiveDataBO monitorLiveDataBO) {
        try{
            PageHelper.startPage(monitorLiveDataBO.getPage(), monitorLiveDataBO.getRows());
            Map<String,Object> re= Bean2Map.trans(monitorLiveDataBO);
            List<MonitorLiveData> list=monitorLiveDataDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
