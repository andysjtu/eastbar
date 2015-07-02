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
import org.eastbar.web.unit.UrlHistoryService;
import org.eastbar.web.unit.biz.UrlHistoryBO;
import org.eastbar.web.unit.dao.UrlHistoryDao;
import org.eastbar.web.unit.entity.UrlHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:32
 * @description :
 */
@Service
@Transactional
public class UrlHistoryServiceImpl implements UrlHistoryService {

    @Autowired
    private UrlHistoryDao urlHistoryDao;

    @Override
    public UrlHistoryBO getUrlHistory(Integer id){
        try {
            UrlHistoryBO urlHistoryBO=new UrlHistoryBO();
            BeanUtils.copyProperties(urlHistoryBO, urlHistoryDao.getUrlHistory(id));
            return urlHistoryBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllUrlHistory(UrlHistoryBO urlHistoryBO){
        try {
            PageHelper.startPage(urlHistoryBO.getPage(), urlHistoryBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(urlHistoryBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<UrlHistory> list = urlHistoryDao.getAllUrlHistory(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(UrlHistoryBO urlHistoryBO){
        try {
            UrlHistory urlHistory=new UrlHistory();
            BeanUtils.copyProperties(urlHistory, urlHistoryBO);
            urlHistoryDao.save(urlHistory);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(UrlHistoryBO urlHistoryBO){
        try {
            UrlHistory urlHistory=new UrlHistory();
            BeanUtils.copyProperties(urlHistory, urlHistoryBO);
            urlHistoryDao.update(urlHistory);
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
            urlHistoryDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
