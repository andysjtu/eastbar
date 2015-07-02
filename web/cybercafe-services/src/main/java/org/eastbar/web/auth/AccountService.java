/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.auth;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.account.RoleService;
import org.eastbar.web.account.UserService;
import org.eastbar.web.account.entity.Role;
import org.eastbar.web.account.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月26
 * @time 下午1:18
 * @description : Shiro帐户管理业务类
 */
@Service
@Transactional
public class AccountService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    /**
     * 按Id获得用户.
     */
    public Boolean updateLoginInfo(User user) {
        return userService.updateLoginInfo(user);
    }

    /**
     * 按Id获得用户.
     */
    public List<String> loadPermissionByRole(Integer id) {
        Role role=new Role();
        try{
            BeanUtils.copyProperties(role,roleService.get(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return role.loadShrioPermissions();
    }

    /**
     * 按登录名查询用户.
     */
    public User findUserByLoginName(String loginName) {
        return  userService.findByLoginName(loginName);
    }
}
