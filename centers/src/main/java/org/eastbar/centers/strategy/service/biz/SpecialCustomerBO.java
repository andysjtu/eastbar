/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.service.biz;

/**
 * @author cindy-jia
 * @date 2015年05月29
 * @time 上午10:44
 * @description :
 */
public class SpecialCustomerBO {

    private Integer id;
    private int certType;
    private String certId;
    private String alarmType;
    private String alarmRank;
    private int verNum;
    private int deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(String alarmRank) {
        this.alarmRank = alarmRank;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
