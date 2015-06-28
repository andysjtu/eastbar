/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月13
 * @time 下午7:24
 * @description :
 */
public class ManageRule implements Serializable{ //t_manage_rule

    private Integer id;//id
    private Integer hourVerNum;//hour_ver_num
    private Integer urlVerNum;//url_ver_num
    private Integer progVerNum;//prog_ver_num
    private Integer specialVerNum;//special_ver_num
    private Integer keywordVerNum;//keyword_ver_num
    private String updateTime;//update_time

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHourVerNum() {
        return hourVerNum;
    }

    public void setHourVerNum(Integer hourVerNum) {
        this.hourVerNum = hourVerNum;
    }

    public Integer getUrlVerNum() {
        return urlVerNum;
    }

    public void setUrlVerNum(Integer urlVerNum) {
        this.urlVerNum = urlVerNum;
    }

    public Integer getProgVerNum() {
        return progVerNum;
    }

    public void setProgVerNum(Integer progVerNum) {
        this.progVerNum = progVerNum;
    }

    public Integer getSpecialVerNum() {
        return specialVerNum;
    }

    public void setSpecialVerNum(Integer specialVerNum) {
        this.specialVerNum = specialVerNum;
    }

    public Integer getKeywordVerNum() {
        return keywordVerNum;
    }

    public void setKeywordVerNum(Integer keywordVerNum) {
        this.keywordVerNum = keywordVerNum;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof ManageRule &&
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
