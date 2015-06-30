/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.customerLog.entity;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 上午9:24
 * @description :
 */
public class CustomerHost implements Serializable {//t_customer_host

    private Integer id;//id
    private Integer cid;//cid
    private String ipAdd;//ipAdd
    private String onlineTime;//onlineTime
    private String offlineTime;//offlineTime
    private String osSystem;//osSystem
    private String version;//version
    private String status;
    private String macAddress;//mac_add

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime;
    }

    public String getOsSystem() {
        return osSystem;
    }

    public void setOsSystem(String osSystem) {
        this.osSystem = osSystem;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
