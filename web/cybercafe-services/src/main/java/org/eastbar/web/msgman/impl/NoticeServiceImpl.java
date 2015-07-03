/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.ipc.dao.MonitorDao;
import org.eastbar.web.msgman.NoticeService;
import org.eastbar.web.msgman.biz.NoticeBO;
import org.eastbar.web.msgman.dao.NoticeDao;
import org.eastbar.web.msgman.entity.Notice;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午4:31
 * @description :
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private MonitorDao monitorDao;

    @Override
    @Transactional
    public Boolean save(NoticeBO noticeBO) {
        try{
            Notice notice=new Notice();
            BeanUtils.copyProperties(notice,noticeBO);
            notice.setStatus(1);
            notice.setCreateTime(Times.now());
            String creator= ShiroCustomUtils.getCurrentUserName();
            notice.setCreator(creator);
            String monitorCodes=noticeBO.getMonitorCode();
            String[] monitorCodeList=monitorCodes.split(",");
            List<String> codes=new ArrayList<>();
            for(String monitorCode:monitorCodeList){
               notice.setMonitorCode(monitorCode);
               noticeDao.save(notice);
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        try{
            noticeDao.delete(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean update(NoticeBO noticeBO) {
        try{
            Notice notice=new Notice();
            BeanUtils.copyProperties(notice,noticeBO);
            noticeDao.update(notice);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NoticeBO get(Integer id) {
        try{
            NoticeBO noticeBO=new NoticeBO();
            BeanUtils.copyProperties(noticeBO,noticeDao.get(id));
            return noticeBO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageInfo getAllPublic(NoticeBO noticeBO) {
        try{
            PageHelper.startPage(noticeBO.getPage(), noticeBO.getRows());
            noticeBO.setCreator(ShiroCustomUtils.getCurrentUserName());
            Map<String,Object> re= Bean2Map.trans(noticeBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));//域
            }

            List list=noticeDao.getAllPublic(re);
            return PageInfo.clone(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PageInfo getAllReceive(NoticeBO noticeBO) {
        try{
            PageHelper.startPage(noticeBO.getPage(), noticeBO.getRows());
            Map<String,Object> re= Bean2Map.trans(noticeBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));//域
            }

            List list=noticeDao.getAllReceive(re);
            return PageInfo.clone(list);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteMany(String[] ids) {
        try{
            if(ids!=null){
                for(int i=0;i<ids.length;i++){
                    Integer id=Integer.parseInt(ids[i]);
                    delete(id);
                }
                return true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
