/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.entity;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月29
 * @time 下午2:06
 * @description :
 */
public class RolePermission implements Serializable {

    private Integer roleId;
    private Integer perId;
    private String createtime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
