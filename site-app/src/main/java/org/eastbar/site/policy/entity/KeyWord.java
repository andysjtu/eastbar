package org.eastbar.site.policy.entity;

/**
 * Created by AndySJTU on 2015/5/12.
 */

import javax.persistence.*;

/**
 * keyword	varchar(20)		关键字
 alarm_type	int(11)		告警类型
 alarm_rank	int(11)		告警等级
 monitor_code	Varchar(255)		监管中心编码
 is_pub	int(1)		是否发布
 is_block	int(11)		是否拦截
 create_time	datetime		创建时间
 creator	varchar(20)		创建人
 udate_time	datetime		更新时间
 updator	varchar(20)		更新人
 version	varchar(20)		版本号
 operation	varchar(20)		操作
 delete_flag	int(1)		删除标志
 ver_num	bigint(20)		版本号内部表示

 */
@Entity
@Table(name="key_word")
public class KeyWord {
    @Id
    private Integer id;
    @Column(length = 500)
    private String keyword;
    private int alarmType;
    private int alarmRank;
    private int isBlock;
//    private boolean deleted;
    private int verNum;

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

//    public boolean isDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(boolean delete) {
//        this.deleted = delete;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int isBlock() {
        return isBlock;
    }

    public void setIsBlock(int isBlock) {
        this.isBlock = isBlock;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }

    @Override
    public String toString() {
        return "KeyWord{" +
                "alarmRank=" + alarmRank +
                ", id=" + id +
                ", keyword='" + keyword + '\'' +
                ", alarmType=" + alarmType +
                ", isBlock=" + isBlock +
                ", verNum=" + verNum +
                '}';
    }
}
