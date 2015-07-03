/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.ProgHistoryService;
import org.eastbar.web.unit.biz.ProgHistoryBO;
import org.eastbar.web.unit.dao.ProgHistoryDao;
import org.eastbar.web.unit.entity.ProgHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:24
 * @description :
 */
@Service
@Transactional
public class ProgHistoryServiceImpl implements ProgHistoryService {

    @Autowired
    private ProgHistoryDao progHistoryDao;

    @Override
    public ProgHistoryBO getProgHistory(Integer id){
        try {
            ProgHistoryBO progHistoryBO=new ProgHistoryBO();
            BeanUtils.copyProperties(progHistoryBO, progHistoryDao.getProgHistory(id));
            return progHistoryBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllProgHistory(ProgHistoryBO progHistoryBO){
        try {
            PageHelper.startPage(progHistoryBO.getPage(), progHistoryBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(progHistoryBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<ProgHistory> list = progHistoryDao.getAllProgHistory(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(ProgHistoryBO progHistoryBO){
        try {
            ProgHistory progHistory=new ProgHistory();
            BeanUtils.copyProperties(progHistory, progHistoryBO);
            progHistoryDao.save(progHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(ProgHistoryBO progHistoryBO){
        try {
            ProgHistory progHistory=new ProgHistory();
            BeanUtils.copyProperties(progHistory, progHistoryBO);
            progHistoryDao.update(progHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id){
        try {
            progHistoryDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
