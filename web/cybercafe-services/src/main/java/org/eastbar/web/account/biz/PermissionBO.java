/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.biz;

import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年10月29
 * @time 下午2:01
 * @description :
 */
public class PermissionBO extends PageInfo {

    private Integer id; //id
    private String permission; //permission
    private String perDesc; //per_desc

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPerDesc() {
        return perDesc;
    }

    public void setPerDesc(String perDesc) {
        this.perDesc = perDesc;
    }
}
