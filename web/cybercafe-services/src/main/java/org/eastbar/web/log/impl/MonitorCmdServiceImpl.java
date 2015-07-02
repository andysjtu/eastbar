/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.log.MonitorCmdService;
import org.eastbar.web.log.biz.MonitorCmdBO;
import org.eastbar.web.log.dao.MonitorCmdDao;
import org.eastbar.web.log.entity.MonitorCmd;
import org.eastbar.web.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月08
 * @time 上午9:45
 * @description :
 */
@Service
public class MonitorCmdServiceImpl implements MonitorCmdService {

    @Autowired
    private MonitorCmdDao monitorCmdDao;

    @Override
    public PageInfo getAll(MonitorCmdBO monitorCmdBO) {
        try{
            PageHelper.startPage(monitorCmdBO.getPage(), monitorCmdBO.getRows());
            Map<String,Object> re= Bean2Map.trans(monitorCmdBO);
            List<MonitorCmd> list=monitorCmdDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MonitorCmdBO get(Integer id) {
        try{
            MonitorCmdBO cmdBO=new MonitorCmdBO();
            BeanUtils.copyProperties(cmdBO, monitorCmdDao.get(id));
            return cmdBO;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(MonitorCmdBO monitorCmdBO) {
        try{
            MonitorCmd monitorCmd=new MonitorCmd();
            BeanUtils.copyProperties(monitorCmd,monitorCmdBO);
            monitorCmdDao.save(monitorCmd);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
