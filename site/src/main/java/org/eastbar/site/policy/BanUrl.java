package org.eastbar.site.policy;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/12.
 */
@Entity
@Table(name="ban_url")
public class BanUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer urlType;
    private String urlValue;
    private int alarmType;
    private boolean isBlock;
    private int alarmRank;
    private int verNum;
    private boolean deleted;

    public int getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(int alarmRank) {
        this.alarmRank = alarmRank;
    }

    public int getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(int alarmType) {
        this.alarmType = alarmType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean delete) {
        this.deleted = delete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public void setIsBlock(boolean isBlock) {
        this.isBlock = isBlock;
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    public String getUrlValue() {
        return urlValue;
    }

    public void setUrlValue(String urlValue) {
        this.urlValue = urlValue;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }
}
