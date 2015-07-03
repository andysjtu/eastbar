/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.PageInfo;
import org.eastbar.web.account.entity.Permission;
import org.eastbar.web.ipc.entity.Monitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月23
 * @time 下午4:50
 * @description :
 */
public class RoleBO extends PageInfo {

    private Integer id; //id
    private String commonName;
    private String roleName; //role_name
    private String roleDesc; //role_desc
    private String type;
    private String createTime;
    private String creator;
    private String updator;
    private String updateTime;
    private String monitorCode;
    private List<Monitor> monitor=new ArrayList<>();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMonitorCode() {
        return monitorCode;
    }

    public void setMonitorCode(String monitorCode) {
        this.monitorCode = monitorCode;
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

    public List<Monitor> getMonitor() {
        return monitor;
    }

    public void setMonitor(List<Monitor> monitor) {
        this.monitor = monitor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
