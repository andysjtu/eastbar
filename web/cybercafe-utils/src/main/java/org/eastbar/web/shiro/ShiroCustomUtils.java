/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.shiro;

import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月26
 * @time 下午3:43
 * @description :
 */
public class ShiroCustomUtils {
    /**
     * 取出Shiro中的当前用户LoginName.
     */
    public static String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            throw new RuntimeException();
        }
        return user.loginName;
    }
    /**
     * 取出Shiro中的当前用户ID.
     */
    public static Integer getCurrentID() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            throw new RuntimeException();
        }
        return user.id;
    }
    /**
     * 取出Shiro中的当前用户上次登录时间LastLogin.
     */
    public static String getCurrentUserLastLogin() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            throw new RuntimeException();
        }
        return user.lastlogin;
    }

    /**
     * 取出Shiro中的当前用户所属的监管中心monitorCodes.
     * @return
     */
    public static List<String> getMonitors(){
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if(user == null){
            throw new RuntimeException();
        }
        return user.monitorCodes;
    }
}
