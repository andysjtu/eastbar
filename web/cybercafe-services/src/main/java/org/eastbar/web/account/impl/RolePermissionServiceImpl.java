/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Times;
import org.eastbar.web.account.RolePermissionService;
import org.eastbar.web.account.biz.RolePermissionBO;
import org.eastbar.web.account.dao.PermissionDao;
import org.eastbar.web.account.dao.RolePermissionDao;
import org.eastbar.web.account.entity.Permission;
import org.eastbar.web.account.entity.RolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cindy-jia
 * @date 2014年10月29
 * @time 下午2:17
 * @description :
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public Boolean save(RolePermissionBO rolePermissionBO) {
        try {
            RolePermission rolePermission=new RolePermission();
            BeanUtils.copyProperties(rolePermission, rolePermissionBO);
            rolePermissionDao.save(rolePermission);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer roleId) {
        try {
            rolePermissionDao.delete(roleId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updatePermissions(String[] permissions,Integer id) {
        Permission permission=new Permission();
        RolePermission rolePermission=new RolePermission();
        try {
            rolePermissionDao.delete(id);
            if(!"".equals(permissions) && permissions!=null){
                for(int i=0;i<permissions.length;i++){
                    permission=permissionDao.byPermisson(permissions[i]);
                    rolePermission.setRoleId(id);
                    rolePermission.setPerId(permission.getId());
                    rolePermission.setCreatetime(Times.now());
                    rolePermissionDao.save(rolePermission);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
