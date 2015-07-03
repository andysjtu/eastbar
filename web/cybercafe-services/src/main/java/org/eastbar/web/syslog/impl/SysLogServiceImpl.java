/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.syslog.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.syslog.SysLogService;
import org.eastbar.web.syslog.biz.SysLogBO;
import org.eastbar.web.syslog.dao.SysLogDao;
import org.eastbar.web.syslog.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void add(SysLog sysLog) {

        sysLogDao.add(sysLog);
    }

    @Override
    public PageInfo getAll(SysLogBO sysLogBO) {
        try{
            PageHelper.startPage(sysLogBO.getPage(), sysLogBO.getRows());
            Map<String,Object> re= Bean2Map.trans(sysLogBO);
            List<SysLog> list=sysLogDao.getAllLog(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
