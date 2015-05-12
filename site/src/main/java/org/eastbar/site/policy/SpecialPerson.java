package org.eastbar.site.policy;

/**
 * Created by AndySJTU on 2015/5/12.
 */

/**
 * 字段名	类型	pk/fk	说明
 id	int	pk
 name	varchar(20)		姓名
 cert_type	int(11)		证件类型
 cert_id	varchar(255)		证件id
 monitor_code	Varchar(255)		监管中心编码
 nationality	varchar(20)		国籍
 alarm_type	int(20)		告警类型
 alarm_rank	int(11)		告警等级
 is_pub	int(11)		是否发布
 create_time	datetime		创建时间
 creator	varchar(20)		创建者
 update_time	datetime		更新时间
 updator	varchar(20)		更新着
 version	varchar(20)		版本号
 operation	varchar(20)		操作
 delete_flag	int(1)		删除标志
 ver_num	bigint(20)		版本号系统内部表示

 */
public class SpecialPerson {
    private Integer id;
    private int certType;
    private String certId;
    private String alarmType;
    private String alarmRank;
    private int verNum;
}
