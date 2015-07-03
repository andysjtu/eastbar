/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.log.OperLogService;
import org.eastbar.web.log.biz.OperLogBO;
import org.eastbar.web.log.dao.OperLogDao;
import org.eastbar.web.log.entity.OperLog;
import org.eastbar.web.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月04
 * @time 下午4:16
 * @description :
 */
@Service
public class OperLogServiceImpl implements OperLogService {

    @Autowired
    private OperLogDao operLogDao;

    @Override
    public PageInfo getAll(OperLogBO operLogBO) {
       try{
           PageHelper.startPage(operLogBO.getPage(), operLogBO.getRows());
           Map<String,Object> re= Bean2Map.trans(operLogBO);
           List<OperLog> list=operLogDao.getAll(re);
           return PageInfo.clone(list);
       }catch(Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public PageInfo getAllAdmin(OperLogBO operLogBO) {
        try{
            PageHelper.startPage(operLogBO.getPage(), operLogBO.getRows());
            Map<String,Object> re= Bean2Map.trans(operLogBO);
            List<OperLog> list=operLogDao.getAllAdmin(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public void add(OperLogBO operLogBO) {
        try {
            OperLog operLog=new OperLog();
            BeanUtils.copyProperties(operLog,operLogBO);
            operLogDao.save(operLog);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
