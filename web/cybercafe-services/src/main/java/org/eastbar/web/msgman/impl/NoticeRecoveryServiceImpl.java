/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.msgman.NoticeRecoveryService;
import org.eastbar.web.msgman.biz.NoticeRecoveryBO;
import org.eastbar.web.msgman.dao.NoticeRecoveryDao;
import org.eastbar.web.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午4:43
 * @description :
 */
@Service
public class NoticeRecoveryServiceImpl implements NoticeRecoveryService {

    @Autowired
    private NoticeRecoveryDao noticeRecoveryDao;

    @Override
    public PageInfo getAll(NoticeRecoveryBO noticeRecoveryBO) {
        try{
            PageHelper.startPage(noticeRecoveryBO.getPage(), noticeRecoveryBO.getRows());
            Map<String,Object> re= Bean2Map.trans(noticeRecoveryBO);
            List list=noticeRecoveryDao.getAll(re);
            return PageInfo.clone(list);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NoticeRecoveryBO get(Integer id) {
        try{
            NoticeRecoveryBO noticeRecoveryBO=new NoticeRecoveryBO();
            BeanUtils.copyProperties(noticeRecoveryBO,noticeRecoveryDao.get(id));
            return noticeRecoveryBO;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
