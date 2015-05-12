package org.eastbar.site.policy;

/**
 * Created by AndySJTU on 2015/5/12.
 */

import javax.persistence.*;

/**
 * prog_type	Int(1)		程序类型
 prog_name	varchar(244)		程序名称
 prog_filename	varchar(20)		程序文件名
 monitor_code	Varchar(255)		监管中心编码
 progress_name	varchar(20)		进程名称
 prog_mf	varchar(50)		制造商
 featuer_code	int(11)		特征码
 alarm_type	int(11)		告警类型
 alarm_rank	varchar(20)		告警等级
 is_pub	int(1)		是否发布
 version	varchar(20)		版本号
 operation	varchar(20)		操作
 delete_flag	int(1)		删除标志
 ver_num	bigint(20)		版本号内部表示

 */
@Entity
@Table(name="ban_prog")
public class BanProg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String progType;
    private String progName;
    private String progressName;
    private String featureCode;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getFeatureCode() {
        return featureCode;
    }

    public void setFeatureCode(String featureCode) {
        this.featureCode = featureCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProgName() {
        return progName;
    }

    public void setProgName(String progName) {
        this.progName = progName;
    }

    public String getProgressName() {
        return progressName;
    }

    public void setProgressName(String progressName) {
        this.progressName = progressName;
    }

    public String getProgType() {
        return progType;
    }

    public void setProgType(String progType) {
        this.progType = progType;
    }

    public int getVerNum() {
        return verNum;
    }

    public void setVerNum(int verNum) {
        this.verNum = verNum;
    }
}
