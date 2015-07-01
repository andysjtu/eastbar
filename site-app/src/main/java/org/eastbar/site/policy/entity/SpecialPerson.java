package org.eastbar.site.policy.entity;

/**
 * Created by AndySJTU on 2015/5/12.
 */

import javax.persistence.*;

/**
 * 字段名	类型	pk/fk	说明
 * id	int	pk
 * name	varchar(20)		姓名
 * cert_type	int(11)		证件类型
 * cert_id	varchar(255)		证件id
 * monitor_code	Varchar(255)		监管中心编码
 * nationality	varchar(20)		国籍
 * alarm_type	int(20)		告警类型
 * alarm_rank	int(11)		告警等级
 * is_pub	int(11)		是否发布
 * create_time	datetime		创建时间
 * creator	varchar(20)		创建者
 * update_time	datetime		更新时间
 * updator	varchar(20)		更新着
 * version	varchar(20)		版本号
 * operation	varchar(20)		操作
 * delete_flag	int(1)		删除标志
 * ver_num	bigint(20)		版本号系统内部表示
 */
@Entity
@Table(name="sp_person")
public class SpecialPerson {
    @Id
    private Integer id;
    private int certType;
    private String certId;
    private String alarmType;
    private String alarmRank;
    private int verNum;
    private boolean deleted;

    public String getAlarmRank() {
        return alarmRank;
    }

    public void setAlarmRank(String alarmRank) {
        this.alarmRank = alarmRank;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }
}
