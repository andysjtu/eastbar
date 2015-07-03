/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.shiro;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月26
 * @time 下午3:48
 * @description :自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public Integer id;
    public String loginName;
    public String name;
    public String lastlogin;
    public List<String> monitorCodes;

    public ShiroUser(Integer id,String loginName, String name,String lastlogin,List<String> monitorCodes) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.lastlogin = lastlogin;
        this.monitorCodes=monitorCodes;
    }

    public String getName() {
        return name;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }

    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(loginName);
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        return true;
    }
}
