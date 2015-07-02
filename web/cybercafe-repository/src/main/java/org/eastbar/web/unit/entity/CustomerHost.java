/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private List<CustomerHistory> customer = new ArrayList<>();

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
        this.onlineTime = onlineTime.substring(0,19);
    }

    public String getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(String offlineTime) {
        this.offlineTime = offlineTime.substring(0,19);
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

    public List<CustomerHistory> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerHistory> customer) {
        this.customer = customer;
    }
}
