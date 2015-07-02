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
 * @date 2014年10月16
 * @time 下午7:54
 * @description :
 */
public class CustomerHistory implements Serializable {//t_customer

    private Integer id;//id
    private String accountId;//account_id
    private String siteCode; //site_code
    private String provinceCode;//province_code
    private String cityCode;//city_code
    private String countyCode;//county_code
    private String name;//name
    private Integer certType;//cert_type
    private String certId;//cert_id
    private String nationality;//nationality
    private String status;//status
    private String openTime;//open_time
    private String closeTime;//close_time

    private List<CustomerHost> customer = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCertType() {
        return certType;
    }

    public void setCertType(Integer certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public List<CustomerHost> getCustomer() {
        return customer;
    }

    public void setCustomer(List<CustomerHost> customer) {
        this.customer = customer;
    }
}
