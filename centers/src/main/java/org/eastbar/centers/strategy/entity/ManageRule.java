/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.entity;

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
    private Long hourVerNum;//hour_ver_num
    private Long urlVerNum;//url_ver_num
    private Long progVerNum;//prog_ver_num
    private Long specialVerNum;//special_ver_num
    private Long keywordVerNum;//keyword_ver_num
    private String updateTime;//update_time

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getProgVerNum() {
        return progVerNum;
    }

    public void setProgVerNum(Long progVerNum) {
        this.progVerNum = progVerNum;
    }

    public Long getHourVerNum() {
        return hourVerNum;
    }

    public void setHourVerNum(Long hourVerNum) {
        this.hourVerNum = hourVerNum;
    }

    public Long getUrlVerNum() {
        return urlVerNum;
    }

    public void setUrlVerNum(Long urlVerNum) {
        this.urlVerNum = urlVerNum;
    }

    public Long getSpecialVerNum() {
        return specialVerNum;
    }

    public void setSpecialVerNum(Long specialVerNum) {
        this.specialVerNum = specialVerNum;
    }

    public Long getKeywordVerNum() {
        return keywordVerNum;
    }

    public void setKeywordVerNum(Long keywordVerNum) {
        this.keywordVerNum = keywordVerNum;
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
