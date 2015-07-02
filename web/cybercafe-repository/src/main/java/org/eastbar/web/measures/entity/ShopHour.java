/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author cindy-jia
 * @date 2014年10月13
 * @time 下午7:31
 * @description :
 */
public class ShopHour implements Serializable {//t_shop_hour

    private Integer id;//id
    private Long startTime1;//start_time
    private Long endTime1;//end_time
    private Integer operStatus1;//oper_status
    private Long startTime2;//start_time
    private Long endTime2;//end_time
    private Integer operStatus2;//oper_status
    private Long startTime3;//start_time
    private Long endTime3;//end_time
    private Integer operStatus3;//oper_status
    private Integer shopHourType;//shop_hour_type
    private Integer dayOfWeek;//day_of_week
    private String startDate;//start_date
    private String endDate;//end_date
    private Integer isPub;//is_pub
    private String version;
    private String operation;
    private Integer deleteFlag;
    private Integer verNum;//版本号的数字表示


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStartTime1() {
        return startTime1;
    }

    public void setStartTime1(Long startTime1) {
        this.startTime1 = startTime1;
    }

    public Long getEndTime1() {
        return endTime1;
    }

    public void setEndTime1(Long endTime1) {
        this.endTime1 = endTime1;
    }

    public Integer getOperStatus1() {
        return operStatus1;
    }

    public void setOperStatus1(Integer operStatus1) {
        this.operStatus1 = operStatus1;
    }

    public Long getStartTime2() {
        return startTime2;
    }

    public void setStartTime2(Long startTime2) {
        this.startTime2 = startTime2;
    }

    public Long getEndTime2() {
        return endTime2;
    }

    public void setEndTime2(Long endTime2) {
        this.endTime2 = endTime2;
    }

    public Integer getOperStatus2() {
        return operStatus2;
    }

    public void setOperStatus2(Integer operStatus2) {
        this.operStatus2 = operStatus2;
    }

    public Long getStartTime3() {
        return startTime3;
    }

    public void setStartTime3(Long startTime3) {
        this.startTime3 = startTime3;
    }

    public Long getEndTime3() {
        return endTime3;
    }

    public void setEndTime3(Long endTime3) {
        this.endTime3 = endTime3;
    }

    public Integer getOperStatus3() {
        return operStatus3;
    }

    public void setOperStatus3(Integer operStatus3) {
        this.operStatus3 = operStatus3;
    }

    public Integer getShopHourType() {
        return shopHourType;
    }

    public void setShopHourType(Integer shopHourType) {
        this.shopHourType = shopHourType;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getIsPub() {
        return isPub;
    }

    public void setIsPub(Integer isPub) {
        this.isPub = isPub;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getVerNum() {
        return verNum;
    }

    public void setVerNum(Integer verNum) {
        this.verNum = verNum;
    }

    @Override
    public boolean equals(Object o) {

        return null != o &&
                o instanceof ShopHour &&
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
