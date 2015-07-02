/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.biz;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.web.PageInfo;

/**
 * @author cindy-jia
 * @date 2014年11月03
 * @time 下午3:05
 * @description :
 */
public class SitePunishBO extends PageInfo {

    private Integer id;//id
    private String siteCode;//site_code
    private String siteName;//site_name
    private String punishTime;//punish_time
    private Integer punishReason;//punish_reason
    private Integer punishType;//punish_type
    private String punishPerson;//punish_person
    private String permitPerson;//permit_person
    private String punishOrg;//punish_org
    private Integer punishResult;//punish_result
    private String createTime;//create_time
    private String creator;//creator


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getPunishTime() {
        return punishTime;
    }

    public void setPunishTime(String punishTime) {
        this.punishTime = punishTime;
    }

    public Integer getPunishReason() {
        return punishReason;
    }

    public void setPunishReason(Integer punishReason) {
        this.punishReason = punishReason;
    }

    public Integer getPunishType() {
        return punishType;
    }

    public void setPunishType(Integer punishType) {
        this.punishType = punishType;
    }

    public String getPunishPerson() {
        return punishPerson;
    }

    public void setPunishPerson(String punishPerson) {
        this.punishPerson = punishPerson;
    }

    public String getPermitPerson() {
        return permitPerson;
    }

    public void setPermitPerson(String permitPerson) {
        this.permitPerson = permitPerson;
    }

    public String getPunishOrg() {
        return punishOrg;
    }

    public void setPunishOrg(String punishOrg) {
        this.punishOrg = punishOrg;
    }

    public Integer getPunishResult() {
        return punishResult;
    }

    public void setPunishResult(Integer punishResult) {
        this.punishResult = punishResult;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
