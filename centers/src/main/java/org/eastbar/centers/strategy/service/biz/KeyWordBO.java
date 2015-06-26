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
public class KeyWordBO {

    private Integer id;
    private String keyword;
    private int alarmType;
    private int alarmRank;
    private int isBlock;
    private int deleted;
    private int verNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public int getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(int alarmRank) {
        this.alarmRank = alarmRank;
    }

    public int getIsBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }
}
