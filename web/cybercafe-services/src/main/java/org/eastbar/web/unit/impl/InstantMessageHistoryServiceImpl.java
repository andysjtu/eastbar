/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.InstantMessageHistoryService;
import org.eastbar.web.unit.biz.InstantMessageHistoryBO;
import org.eastbar.web.unit.dao.InstantMessageHistoryDao;
import org.eastbar.web.unit.entity.InstantMessageHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月05
 * @time 上午10:50
 * @description :
 */
@Service
public class InstantMessageHistoryServiceImpl implements InstantMessageHistoryService {

    @Autowired
    private InstantMessageHistoryDao instantMessageHistoryDao;

    @Override
    public PageInfo getAll(InstantMessageHistoryBO instantMessageHistoryBO) {
        try{
            PageHelper.startPage(instantMessageHistoryBO.getPage(), instantMessageHistoryBO.getRows());
            Map<String,Object> re= Bean2Map.trans(instantMessageHistoryBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));
            }
            List<InstantMessageHistory> list=instantMessageHistoryDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception  e){
            e.printStackTrace();
        }
        return null;
    }
}
