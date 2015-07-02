/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.entity;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.ipc.entity.Monitor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月25
 * @time 下午3:36
 * @description :
 */
public class Role implements Serializable { //t_role

    private Integer id; //id
    private String commonName;//common_name
    private String roleName; //role_name
    private String roleDesc; //role_desc
    private String monitorCode;//monitorCode
    private List<Monitor> monitor;

    private List<Permission> permissions = new ArrayList<>();
    private List<String> shrioPermissions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<String> getShrioPermissions() {
        return shrioPermissions;
    }

    public void setShrioPermissions(List<String> shrioPermissions) {
        this.shrioPermissions = shrioPermissions;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
    }

    public List<Monitor> getMonitor() {
        return monitor;
    }

    public void setMonitor(List<Monitor> monitor) {
        this.monitor = monitor;
    }

    public List<String> loadShrioPermissions(){
        return ImmutableList.copyOf(shrioPermissions);
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof Role &&
                EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
