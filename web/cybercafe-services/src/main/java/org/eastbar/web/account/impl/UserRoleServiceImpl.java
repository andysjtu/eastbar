/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Times;
import org.eastbar.web.account.UserRoleService;
import org.eastbar.web.account.biz.UserRoleBO;
import org.eastbar.web.account.dao.UserRoleDao;
import org.eastbar.web.account.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月27
 * @time 上午11:51
 * @description :
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public Boolean save(UserRoleBO userRoleBO) {
        UserRole ur=new UserRole();
        try {
            BeanUtils.copyProperties(ur, userRoleBO);
            ur.setCreatetime(Times.now());
            userRoleDao.save(ur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Boolean delete(UserRoleBO userRoleBO) {
        UserRole ur=new UserRole();
        try {
            BeanUtils.copyProperties(ur, userRoleBO);
            userRoleDao.delete(ur);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean edit(UserRoleBO userRoleBO) {
        UserRole ur=new UserRole();
        try{
            BeanUtils.copyProperties(ur, userRoleBO);
            userRoleDao.delete(ur);
            ur.setCreatetime(Times.now());
            if(ur.getRoleId()>0){
                userRoleDao.save(ur);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<UserRoleBO> get(Integer userId) {
        try{
            List<UserRole> userRoles=userRoleDao.get(userId);
            List<UserRoleBO> userRoleBOs=new ArrayList<>(userRoles.size());
            if(userRoles!=null){
                for(int i=0;i<userRoles.size();i++){
                    UserRoleBO userRoleBO=new UserRoleBO();
                    BeanUtils.copyProperties(userRoleBO,userRoles.get(i));
                    userRoleBOs.add(i, userRoleBO);
                }
            }
            return userRoleBOs;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
