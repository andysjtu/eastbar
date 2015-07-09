/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.common.redis.WebRedisService;
import org.eastbar.common.rmi.RmiService;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.account.dao.RoleDao;
import org.eastbar.web.account.dao.UserRoleDao;
import org.eastbar.web.account.entity.UserRole;
import org.eastbar.web.ipc.MonitorLiveDataService;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.biz.MonitorBO;
import org.eastbar.web.ipc.dao.MonitorDao;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.ipc.entity.MonitorLiveData;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.entity.ManageRule;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.AlarmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:21
 * @description :
 */
@Service
@Transactional
public class MonitorServiceImpl implements MonitorService {

    @Autowired
    private MonitorDao monitorDao;
    @Autowired
    private MonitorLiveDataService monitorLiveDataService;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RmiService rmiService;
    @Autowired
    private ManageRuleDao manageRuleDao;
    @Autowired
    private WebRedisService redisBasicService;
    @Autowired
    private AlarmHistoryService alarmHistoryService;

    @Override
    public PageInfo byParentCode(MonitorBO monitorBO) {
        List<Monitor> list;
        try {
            PageHelper.startPage(monitorBO.getPage(), monitorBO.getRows());
            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(monitorBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                if("-1".equals(monitorBO.getParentCode())){
                    re.remove("parentCode");
                    re.put("monitor",monitorCodes.get(0));//为了和查询条件中的monitorCode区别开来，用户自带的用monitor表示
                }
                list = monitorDao.byParentCodeSe(re);
            }else{
                if("-1".equals(monitorBO.getParentCode())){
                    list = monitorDao.byParentCode(re);
                }else{
                    list = monitorDao.byParentCodeSe(re);
                }
            }
            List<MonitorBO> mbl = new ArrayList<>();
            try{
                //TODO 连接REDIS获取实时统计数据
                for(Monitor m:list){
                    MonitorBO mb=new MonitorBO();
                    BeanUtils.copyProperties(mb,m);

                    //获取版本信息 begin
                    ManageRule manageRule=manageRuleDao.get();
                    mb.setHourVer(manageRule.getOperHourVer());
                    mb.setProgVer(manageRule.getBanProgVer());
                    mb.setUrlVer(manageRule.getBanUrlVer());
                    mb.setSpecialVer(manageRule.getSpecialPersonVer());
                    //获取版本信息 end
                    if(m.getMonitorCode().length()>4){
                        Map<String,String> monitor=redisBasicService.getMonitorHash(m.getMonitorCode());
                        if(monitor.size()>0){
                            BeanUtils.populate(mb,monitor);
                            mb.setStatus(0);//正常
                        }else{
                            mb.setStatus(1);//故障
                        }
                    }else{
                        Map<String,String> center=redisBasicService.getCeneterHash(m.getMonitorCode());
                        if(center.size()>0){
                            BeanUtils.populate(mb,center);
                            mb.setStatus(0);//正常
                        }else{
                            mb.setStatus(1);//故障
                        }
                    }
                    Long counts=alarmHistoryService.getCountByCode(mb.getMonitorCode());
                    mb.setTotalAlarm(counts);
                    mbl.add(mb);
                }

            }catch(Exception e){
                Map<String,MonitorLiveData> liveLatest = monitorLiveDataService.getAllLatest();
                for(Monitor m:list){
                    MonitorBO mb = new MonitorBO();
                    BeanUtils.copyProperties(mb, m);
                    if(liveLatest.containsKey(m.getMonitorCode())){
                        MonitorLiveData monitorLiveData=liveLatest.get(m.getMonitorCode());
                        BeanUtils.copyProperties(mb,monitorLiveData);
                    }
                    mbl.add(mb);
                }
            }
            return PageInfo.clone(list, mbl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public MonitorBO get(String monitorCode) {
        try {
            MonitorBO monitorBO = new MonitorBO();
            BeanUtils.copyProperties(monitorBO,monitorDao.get(monitorCode));
            return monitorBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    @Transactional
    public Boolean save(MonitorBO monitorBO) {
        Monitor monitor = new Monitor();
        try {
            BeanUtils.copyProperties(monitor,monitorBO);
            if("-1".equals(monitor.getParentCode())){
                monitor.setParentCode(null);
                monitor.setType(0);
            }
            monitor.setCreateTime(Times.now());
            monitor.setCreator(ShiroCustomUtils.getCurrentUserName());
            monitor.setHourVer("1.0.0");
            monitor.setKeyWordVer("1.0.0");
            monitor.setUrlVer("1.0.0");
            monitor.setProgVer("1.0.0");
            monitor.setSpecialVer("1.0.0");
            monitorDao.save(monitor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(MonitorBO monitorBO) {
        Monitor monitor = new Monitor();
        try {
            BeanUtils.copyProperties(monitor,monitorBO);
            if("-1".equals(monitor.getParentCode())){
                monitor.setParentCode(null);
            }
            monitor.setUpdateTime(Times.now());
            monitor.setUpdator(ShiroCustomUtils.getCurrentUserName());
            monitorDao.update(monitor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(String monitorCode) {
        try {
            roleDao.deleteByMonitorCode(monitorCode);
            monitorDao.deleteByParent(monitorCode);
            monitorDao.delete(monitorCode);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Boolean deleteMany(String[] monitorCodes) {
        try {
            for(int i=0;i<monitorCodes.length;i++){
                Integer id=Integer.parseInt(monitorCodes[i]+"");
                roleDao.deleteByMonitorCode(monitorCodes[i]);
                monitorDao.deleteByParent(monitorCodes[i]);
                monitorDao.delete(monitorCodes[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Monitor> getMonitors() {
        List<Monitor> monitors=getUserMonitors();
        Map<String,Object> re=new HashMap<>();
       if(monitors.size()==0){
           re.put("parentCode",null);
         return  monitorDao.byParentCode(re);
       }
       else{
           re.put("parentCode", monitors.get(0).getMonitorCode());
           return  monitorDao.byParentCodeSe(re);

       }

    }

    @Override
    public List<Monitor> getPlaceMonitors(){
        List<Monitor> monitors=getUserMonitors();
        Map<String,Object> re=new HashMap<>();
        if(monitors.size()==0){
            re.put("parentCode",null);
            List<Monitor> monitorList=monitorDao.byParentCode(re);
            List<Monitor> placeMonitors=new ArrayList<>();
            for(Monitor m:monitorList){
                Map<String,Object> map=new HashMap<>();
                map.put("parentCode",m.getMonitorCode());
                placeMonitors.addAll(monitorDao.byParentCodeSe(map));
            }
            return placeMonitors;
        }else{
            List<Monitor> placeMonitors=new ArrayList<>();
            for(Monitor m:monitors){
                Map<String,Object> map=new HashMap<>();
                map.put("parentCode",m.getMonitorCode());
                placeMonitors.addAll(monitorDao.byParentCodeSe(map));
            }
            return placeMonitors;
        }
    }

    @Override
    public List<Monitor> getMonitorsByType(Integer type) {
        if(type==2){
            Map<String,Object> re=new HashMap<>();
            re.put("parentCode",null);
            return  monitorDao.byParentCode(re);
        }
        return null;
    }

    public List<Monitor> getUserMonitors(){
        Integer id=ShiroCustomUtils.getCurrentID();
        List<String> monitorCodes=ShiroCustomUtils.getMonitors();
        List<UserRole> userRoles=userRoleDao.get(id);
        List<Monitor> monitors=roleDao.getRoleMonitor(userRoles.get(0).getRoleId()).getMonitor();//通过roleId获取它所在的监管中心
        return monitors;
    }

    @Override
    public List<Monitor> getMonitorListByParent(String parentCode) {
        Map<String,Object> re=new HashMap<>();
        re.put("parentCode",parentCode);
        return  monitorDao.byParentCodeSe(re);
    }

	@Override
	public List<Monitor> getArea() {
		return monitorDao.getArea();
	}

}