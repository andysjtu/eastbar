/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.web.auth.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.eastbar.web.OracleTimes;
import org.eastbar.web.Times;
import org.eastbar.web.account.RoleService;
import org.eastbar.web.account.UserRoleService;
import org.eastbar.web.account.biz.UserRoleBO;
import org.eastbar.web.account.entity.Role;
import org.eastbar.web.account.entity.User;
import org.eastbar.web.auth.AccountService;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.shiro.ShiroUser;
import org.springside.modules.utils.Encodes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月26
 * @time 下午1:04
 * @description : Shiro主控制器，授权与认证
 */
public class ShiroDbRealm  extends AuthorizingRealm {

    protected AccountService accountService;

    protected UserRoleService userRoleService;

    protected RoleService roleService;
    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(AccountService.HASH_ALGORITHM);
        matcher.setHashIterations(AccountService.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = accountService.findUserByLoginName(token.getUsername());
        List<String> monitorCodes=new ArrayList<>();
        if (user != null) {
            if (user.getStatus()==1) {
                lock:if(user.getLastlogin() != null){
                    Long t = System.currentTimeMillis()- Times.t2long(user.getLastlogin());
                    if(t<1800000){
                        throw new DisabledAccountException();
                    }
                }else {
                    throw new DisabledAccountException();
                }
            }else{
                List<UserRoleBO> userRoles=userRoleService.get(user.getId());//根据用户id得到关系表
                List<Monitor> monitors=new ArrayList<>();
                //循环得到所有的监管中心
                for(int i=0;i<userRoles.size();i++){
                    monitors.addAll(roleService.getRoleMonitor(userRoles.get(i).getRoleId()).getMonitor());
                }
                //循环取出所有的监管中心编码
                for(int i=0;i<monitors.size();i++){
                    monitorCodes.add(monitors.get(i).getMonitorCode());
                }
            }

            byte[] salt = Encodes.decodeHex(user.getSalt());
            return new SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getLoginName(), user.getCommonName(),user.getLastlogin(),monitorCodes), user.getPassword(),
                    ByteSource.Util.bytes(salt), getName());
        } else {
            throw new UnknownAccountException();
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        User user = accountService.findUserByLoginName(shiroUser.loginName);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 基于Role的权限信息
            info.addRole(role.getRoleName());

            // 基于Permission的权限信息
            info.addStringPermissions(accountService.loadPermissionByRole(role.getId()));
        }
        return info;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
