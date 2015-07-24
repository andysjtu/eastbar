/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.account.RoleService;
import org.eastbar.web.account.biz.RoleBO;
import org.eastbar.web.account.dao.RoleDao;
import org.eastbar.web.account.dao.RolePermissionDao;
import org.eastbar.web.account.dao.UserRoleDao;
import org.eastbar.web.account.entity.Role;
import org.eastbar.web.account.entity.RolePermission;
import org.eastbar.web.account.entity.UserRole;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.dao.MonitorDao;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月28
 * @time 上午9:57
 * @description :
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private MonitorDao monitorDao;

    @Autowired
    private MonitorService monitorService;

    @Override
    public RoleBO get(Integer id) {
        try {
            RoleBO roleBO=new RoleBO();
            BeanUtils.copyProperties(roleBO, roleDao.get(id));
            return roleBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoleBO getRoleMonitor(Integer id) {
        try {
            RoleBO roleBO=new RoleBO();
            BeanUtils.copyProperties(roleBO, roleDao.getRoleMonitor(id));
            return roleBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Boolean delete(Integer id) {
        try {
            rolePermissionDao.delete(id);
            roleDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public PageInfo getAllRole(RoleBO roleBO){
        try {
            //PageHelper.startPage(roleBO.getPage(), roleBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(roleBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitorCode",monitorCodes.get(0));
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);

            Integer startRow = roleBO.getPage() > 0 ? (roleBO.getPage() - 1) * roleBO.getRows() : 0;
            re.put("startRow",startRow);
            re.put("endRow",startRow + roleBO.getRows() * (roleBO.getPage() > 0 ? 1 : 0));

            List<Role> list = roleDao.getAllRole(re);

            roleBO.setListing(list);
            roleBO.setTotal(roleDao.getAllRoleCount(re));
            return roleBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getRoles() {
        Integer id=ShiroCustomUtils.getCurrentID();
        List<UserRole> userRoles=userRoleDao.get(id);
        List<Monitor> monitors=roleDao.get(userRoles.get(0).getRoleId()).getMonitor();
        if(monitors.size()==0){
            return null;
        }else{
            return roleDao.byMonitorCode(monitors.get(0).getMonitorCode());
        }
    }

    @Override
    public Boolean save(RoleBO roleBO) {
        try {
            Role role=new Role();
            BeanUtils.copyProperties(role, roleBO);
            roleDao.save(role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(RoleBO roleBO) {
        try {
            Role role=new Role();
            BeanUtils.copyProperties(role, roleBO);
            roleDao.update(role);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
     }

    @Override
    public Boolean deleteMany(String[] ids) {
        try {
            for(int i=0;i<ids.length;i++){
                Integer id=Integer.parseInt(ids[i]+"");
                rolePermissionDao.delete(id);
                delete(id);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Monitor> showMonitors(Integer id) {

        List<UserRole> userRoles=userRoleDao.get(id);//通过用户id获得它的角色
        List<Monitor> monitors=roleDao.getRoleMonitor(userRoles.get(0).getRoleId()).getMonitor();//通过roleId获取它所在的监管中心

        List<Monitor> monitorList=new ArrayList<>();

        if(monitors.size()==0){
            Map<String,Object> re=new HashMap<>();
            re.put("parentCode",null);
            List<Monitor> monitorss=monitorDao.byParentCode(re);
            monitorList.addAll(monitorss);
            for(Monitor m:monitorss){
                re.put("parentCode",m.getMonitorCode());
                List<Monitor> ms=monitorDao.byParentCodeSe(re);
                monitorList.addAll(ms);
            }
        }
        else if(monitors.get(0).getMonitorCode().length()==4){
            Map<String,Object> re=new HashMap<>();
            re.put("parentCode",monitors.get(0).getMonitorCode());
            monitorList=monitorDao.byParentCodeSe(re);
        }

            return monitorList;
    }

    @Override
    public List<Role> getRoles(String monitor) {
        return roleDao.byMonitorCode(monitor);
    }


}
