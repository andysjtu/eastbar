/*
SQLyog 企业版 - MySQL GUI v5.02
主机 - 5.5.20 : 数据库 - cybercafe
*********************************************************************
服务器版本 : 5.5.20
*/


create database if not exists `cybercafe`;

USE `cybercafe`;

/*数据表 `t_alarm_history` 的表结构*/

DROP TABLE IF EXISTS `t_alarm_history`;

CREATE TABLE `t_alarm_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `host_ip` varchar(255) DEFAULT NULL,
  `alarm_time` datetime DEFAULT NULL,
  `alarm_type` int(11) DEFAULT NULL,
  `alarm_content` varchar(255) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_cert_type` int(11) DEFAULT NULL,
  `customer_cert_id` varbinary(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_alarm_history` 的数据*/

insert into `t_alarm_history` values 

(1,'3010','101','020','020','03','172.16.51.134','2014-10-23 15:15:15',1,NULL,'clock',1,'134521198012041835'),

(2,'3010','101','020','020','03','172.16.51.136','2014-10-23 15:15:15',2,NULL,'charc',2,'201144458746325174');

/*数据表 `t_alarm_notify` 的表结构*/

DROP TABLE IF EXISTS `t_alarm_notify`;

CREATE TABLE `t_alarm_notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notifier_type` int(11) DEFAULT NULL,
  `monitor_code` varchar(11) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `sender_pass` varchar(255) DEFAULT NULL,
  `smtp_address` varchar(255) DEFAULT NULL,
  `smtp_port` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*数据表 `t_alarm_notify` 的数据*/

insert into `t_alarm_notify` values 

(4,1,'3010','15230548576','18951243642','',NULL,NULL),

(6,2,'3010','hbjiaxd@126.com','pywj@cybera.com','','172.16.51.126','421'),

(11,1,'3010','15348566574','18951243642',NULL,NULL,NULL),

(13,2,'3010','xinguan@163.com','pywj@cybera.com','','172.16.51.126','421');

/*数据表 `t_banned_prog` 的表结构*/

DROP TABLE IF EXISTS `t_banned_prog`;

CREATE TABLE `t_banned_prog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prog_name` varchar(244) DEFAULT NULL,
  `prog_filename` varchar(20) DEFAULT NULL,
  `progress_name` varchar(20) DEFAULT NULL,
  `prog_mf` varchar(50) DEFAULT NULL,
  `feature_code` int(11) DEFAULT NULL,
  `alarm_type` int(11) DEFAULT NULL,
  `alarm_rank` varchar(20) DEFAULT NULL,
  `is_pub` int(1) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*数据表 `t_banned_prog` 的数据*/

insert into `t_banned_prog` values 

(1,'souhu','souhu','souhu','souhu',1,4,'3',1,'1.0.1','add',0),

(3,'baidu','baidu','baidu.exe','??',123,1,'3',1,'1.0.1','edit',0),

(4,'baidu','baidu','baidu.exe','baidu',123,1,'2',NULL,'1.0.0','add',0),

(9,'11','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(10,'22','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(11,'33','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(12,'44','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(13,'55','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(14,'66','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(15,'77','','','',NULL,1,'1',NULL,'1.0.0','add',0),

(16,'88','','','',NULL,1,'1',NULL,'1.0.0','remove',1),

(17,'88','88','88','88',88,1,'3',NULL,'1.0.0','add',0);

/*数据表 `t_banned_url` 的表结构*/

DROP TABLE IF EXISTS `t_banned_url`;

CREATE TABLE `t_banned_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url_type` int(11) DEFAULT NULL,
  `url_value` varchar(20) DEFAULT NULL,
  `alarm_type` int(11) DEFAULT NULL,
  `is_block` int(11) DEFAULT NULL,
  `alarm_rank` varchar(11) DEFAULT NULL,
  `monitor_code` int(11) DEFAULT NULL,
  `is_pub` int(11) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*数据表 `t_banned_url` 的数据*/

insert into `t_banned_url` values 

(14,1,'www.baidu.com',3,0,'2',3101,0,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(16,3,'tv.sohu.com',2,0,'3',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(18,2,'172.16.51.146',2,0,'3',3101,1,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.1',NULL,0),

(19,2,'172.16.51.135',2,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(21,1,'ddddd',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(24,1,'test.com',1,0,'3',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(25,1,'11',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(26,1,'22',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(27,1,'33',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(28,1,'44',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(29,1,'55',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15',NULL,NULL,'1.0.0',NULL,0),

(30,1,'6611',1,0,'1',3101,NULL,NULL,'2014-10-23 15:15:15','admin','2014-11-04 13:30:48','1.0.0','edit',0),

(32,1,'7711',1,1,'3',3101,NULL,'admin','2014-11-04 13:07:01','admin','2014-11-04 13:07:59','1.0.0','edit',0),

(33,2,'172.16.51.234',3,0,'3',3101,NULL,'admin','2014-11-04 13:11:09',NULL,NULL,'1.0.0','add',0),

(34,2,'172.16.51.421',2,0,'1',3101,NULL,'admin','2014-11-04 13:34:45',NULL,NULL,'1.0.0','add',0),

(35,2,'1111',2,0,'1',3101,NULL,'admin','2014-11-04 13:57:46',NULL,NULL,'1.0.0','add',0);

/*数据表 `t_customer` 的表结构*/

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(255) DEFAULT NULL,
  `site_code` int(11) DEFAULT NULL,
  `province_code` int(11) DEFAULT NULL,
  `city_code` int(11) DEFAULT NULL,
  `county_code` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cert_type` int(11) DEFAULT NULL,
  `cert_id` varchar(20) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `open_time` datetime DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_customer` 的数据*/

insert into `t_customer` values 

(1,'1001',3010,20,20,1,'zhudi',1,'120164198305044215','china',1,'2014-10-23 15:15:15','2014-10-23 15:15:15'),

(2,'1002',3010,20,20,3,'grose',1,'132172198605124245','china',1,'2014-10-23 15:15:15','2014-10-23 15:15:15');

/*数据表 `t_customer_host` 的表结构*/

DROP TABLE IF EXISTS `t_customer_host`;

CREATE TABLE `t_customer_host` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `ip_Add` varchar(255) DEFAULT NULL,
  `online_time` datetime DEFAULT NULL,
  `offline_time` datetime DEFAULT NULL,
  `os_system` varchar(255) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*数据表 `t_customer_host` 的数据*/

insert into `t_customer_host` values 

(1,1,'172.168.51.146','2014-10-23 15:15:15','2014-10-23 16:15:15','win 7','1'),

(2,1,'172.168.51.238','2014-10-23 15:15:15','2014-10-23 16:15:15','win 7','1'),

(3,2,'192.168.1.25','2014-10-23 15:15:15','2014-10-23 16:15:15','win 7','1');

/*数据表 `t_instant_message_history` 的表结构*/

DROP TABLE IF EXISTS `t_instant_message_history`;

CREATE TABLE `t_instant_message_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `host_ip` varchar(20) DEFAULT NULL,
  `program_name` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_id_type` int(11) DEFAULT NULL,
  `instant_type` int(11) DEFAULT NULL,
  `prog_account` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_instant_message_history` 的数据*/

/*数据表 `t_keyword` 的表结构*/

DROP TABLE IF EXISTS `t_keyword`;

CREATE TABLE `t_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(20) DEFAULT NULL,
  `alarm_rank` int(11) DEFAULT NULL,
  `is_block` int(11) DEFAULT NULL,
  `is_pub` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyword` (`keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_keyword` 的数据*/

/*数据表 `t_mail_history` 的表结构*/

DROP TABLE IF EXISTS `t_mail_history`;

CREATE TABLE `t_mail_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(11) DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `host_id` varchar(255) DEFAULT NULL,
  `email_type` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_id_type` int(11) DEFAULT NULL,
  `email_account` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_mail_history` 的数据*/

/*数据表 `t_manage_rule` 的表结构*/

DROP TABLE IF EXISTS `t_manage_rule`;

CREATE TABLE `t_manage_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oper_hour_ver` int(11) DEFAULT NULL,
  `ban_url_ver` int(11) DEFAULT NULL,
  `special_person_ver` int(11) DEFAULT NULL,
  `ban_prog_ver` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_manage_rule` 的数据*/

/*数据表 `t_monitor_cmd` 的表结构*/

DROP TABLE IF EXISTS `t_monitor_cmd`;

CREATE TABLE `t_monitor_cmd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cmd_type` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_id_type` int(11) DEFAULT NULL,
  `host_ip` varchar(255) DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `commander` varchar(20) DEFAULT NULL,
  `cmd_time` datetime DEFAULT NULL,
  `is_success` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_monitor_cmd` 的数据*/

/*数据表 `t_monitor_info` 的表结构*/

DROP TABLE IF EXISTS `t_monitor_info`;

CREATE TABLE `t_monitor_info` (
  `monitor_code` varchar(255) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `parent_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `principal` varchar(255) DEFAULT NULL,
  `principal_tel` varchar(255) DEFAULT NULL,
  `contact_person` varchar(255) DEFAULT NULL,
  `contact_tel` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`monitor_code`),
  KEY `PARENT_CODE` (`parent_code`),
  CONSTRAINT `t_monitor_info_ibfk_1` FOREIGN KEY (`PARENT_CODE`) REFERENCES `t_monitor_info` (`MONITOR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_monitor_info` 的数据*/

insert into `t_monitor_info` values 

('3101',1,NULL,'??????????','aa','020001','aaa','18217327486','aa','18217327486','10202010@qq.com','2014-10-23 15:15:15',NULL,'2014-10-23 15:15:15',NULL),

('310101',2,'3101','?????????','aa11','020011','aa11','aa11','aa11','15230356295','45213686@126.com','2014-10-23 15:15:15',NULL,'2014-11-06 11:01:28','admin'),

('310102',2,'3101','?????????','cc','020002','cc','18241234658','cc','15423648514','52145452125@163.com','2014-10-23 15:15:15','','2014-11-06 11:01:45','admin'),

('3102',1,NULL,'??????????','bb','020001','bb','18870184253','bb','18870125623','1152421@qq.com','2014-10-23 15:15:15',NULL,'2014-10-23 15:15:15',NULL),

('310201',2,'3102','dd11','ddd','020003','dd11','18725345162','ddd','18546236785','23546152485@126.com','2014-10-23 15:15:15','','2014-10-23 15:15:15',''),

('310202',2,'3102','dd22','ddd','020003','dd22','18534625742','ddd','18524625742','1485235@126.com','2014-10-23 15:15:15','','2014-10-23 15:15:15','');

/*数据表 `t_monitor_live` 的表结构*/

DROP TABLE IF EXISTS `t_monitor_live`;

CREATE TABLE `t_monitor_live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `monitor_code` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `total_site` bigint(16) DEFAULT NULL,
  `permit_site` bigint(16) DEFAULT NULL,
  `setup_site` bigint(16) DEFAULT NULL,
  `open_site` bigint(16) DEFAULT NULL,
  `total_terminal` bigint(16) DEFAULT NULL,
  `total_alarm` bigint(16) DEFAULT NULL,
  `total_punish` bigint(16) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*数据表 `t_monitor_live` 的数据*/

insert into `t_monitor_live` values 

(1,'3101',0,1530,1480,1400,1289,8900,1340,13,'2014-10-23 15:15:15'),

(2,'3102',0,2870,2800,2658,2400,72364,2534,25,'2014-10-23 15:15:15'),

(3,'3101',0,1680,1532,1483,1350,865324,1241,24,'2014-10-23 15:15:15'),

(4,'3102',0,2900,2843,2740,2740,4457854,2421,1,'2014-10-23 15:15:15'),

(5,'310101',0,12445,12000,12145,4447,145212,114,44,'2014-10-23 15:15:15'),

(6,'310102',0,25341,24132,145455,145255,19444,457,42,'2014-10-23 15:15:15');

/*数据表 `t_notice` 的表结构*/

DROP TABLE IF EXISTS `t_notice`;

CREATE TABLE `t_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note_range_type` int(11) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  `notice_content` varchar(255) DEFAULT NULL,
  `notice_time` datetime DEFAULT NULL,
  `notice_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_notice` 的数据*/

/*数据表 `t_notice_recovery` 的表结构*/

DROP TABLE IF EXISTS `t_notice_recovery`;

CREATE TABLE `t_notice_recovery` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `notice_title` varchar(255) DEFAULT NULL,
  `notice_content` varchar(255) DEFAULT NULL,
  `notice_time` datetime DEFAULT NULL,
  `notice_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_notice_recovery` 的数据*/

/*数据表 `t_notify_log` 的表结构*/

DROP TABLE IF EXISTS `t_notify_log`;

CREATE TABLE `t_notify_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notify_type` int(11) DEFAULT NULL,
  `receiver` varchar(255) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `sender` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `message_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_notify_log` 的数据*/

/*数据表 `t_oper_log` 的表结构*/

DROP TABLE IF EXISTS `t_oper_log`;

CREATE TABLE `t_oper_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `oper_time` datetime DEFAULT NULL,
  `oper_log` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_oper_log` 的数据*/

/*数据表 `t_permission` 的表结构*/

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) DEFAULT NULL,
  `per_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*数据表 `t_permission` 的数据*/

insert into `t_permission` values 

(1,'account:user:view','ABC'),

(2,'account:user:select','QWE'),

(3,'account:user:add',''),

(4,'account:user:edit',''),

(5,'account:user:delete',''),

(6,'account:user:editRole',''),

(7,'account:role:view',''),

(8,'account:role:add',''),

(9,'account:role:edit',''),

(10,'account:role:delete',''),

(11,'account:role:editPermission',''),

(12,'sysm:leftMenu:account',''),

(13,'account:user:all',''),

(14,'sysm:leftMenu:measures',''),

(15,'measures:shopHour:all',''),

(16,'measures:shopHour:add',''),

(17,'measures:shopHour:edit',''),

(18,'measures:shopHour:delete',''),

(19,'measures:shopHour:public',''),

(20,'measures:url:all',''),

(21,'measures:url:view',''),

(22,'measures:url:select',''),

(23,'measures:url:add',''),

(24,'measures:url:edit',''),

(25,'measures:url:delete',''),

(26,'measures:url:public','');

/*数据表 `t_prog_history` 的表结构*/

DROP TABLE IF EXISTS `t_prog_history`;

CREATE TABLE `t_prog_history` (
  `customer_id` varchar(20) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `host_ip` varchar(20) DEFAULT NULL,
  `program_name` varchar(255) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_id_type` int(11) DEFAULT NULL,
  `is_block` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_prog_history` 的数据*/

insert into `t_prog_history` values 

('3001421545214512',1,3003,'3012','3010','020','020','020','172.16.51.128','qq','2014-10-23 15:15:15',NULL,'jack',1,1),

('1245144532144221',2,3012,'3010','3010','020','020','020','172.16.51.156','feixin','2014-10-23 15:15:15',NULL,'json',1,1);

/*数据表 `t_rank` 的表结构*/

DROP TABLE IF EXISTS `t_rank`;

CREATE TABLE `t_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_name` varchar(20) DEFAULT NULL,
  `is_sms` int(11) DEFAULT NULL,
  `is_email` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*数据表 `t_rank` 的数据*/

insert into `t_rank` values 

(1,'????',0,0,NULL,NULL,'2014-11-05 15:22:51',''),

(2,'??????',0,0,'2014-10-23 15:15:15','','2014-10-23 15:15:15',''),

(3,'????',1,1,'2014-10-23 15:15:15','','2014-10-23 15:15:15','');

/*数据表 `t_role` 的表结构*/

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `common_name` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `monitor_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t_role` (`monitor_code`),
  CONSTRAINT `t_role_ibfk_1` FOREIGN KEY (`monitor_code`) REFERENCES `t_monitor_info` (`monitor_code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*数据表 `t_role` 的数据*/

insert into `t_role` values 

(1,NULL,'admins','this is administrators',NULL),

(2,NULL,'logs','this is log managment',NULL),

(4,'???????','bbc','bbc','3101'),

(16,'test','test','aa','3101'),

(18,'???????','99','99','3102'),

(19,'?????','ddd11admin','ddd11admin','3101'),

(21,'?????','testman','??commonName','3101'),

(22,'test','test','','310101');

/*数据表 `t_role_permission` 的表结构*/

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `per_id` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`role_id`,`per_id`),
  KEY `per_id` (`per_id`),
  CONSTRAINT `t_role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `t_role_permission_ibfk_2` FOREIGN KEY (`per_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_role_permission` 的数据*/

insert into `t_role_permission` values 

(1,1,'2014-11-03 19:19:15'),

(1,2,'2014-11-03 19:19:15'),

(1,3,'2014-11-03 19:19:15'),

(1,4,'2014-11-03 19:19:15'),

(1,5,'2014-11-03 19:19:15'),

(1,6,'2014-11-03 19:19:15'),

(1,7,'2014-11-03 19:19:15'),

(1,8,'2014-11-03 19:19:15'),

(1,9,'2014-11-03 19:19:15'),

(2,1,'2014-10-23 15:15:15'),

(2,2,'2014-10-23 15:15:15');

/*数据表 `t_shop_hour` 的表结构*/

DROP TABLE IF EXISTS `t_shop_hour`;

CREATE TABLE `t_shop_hour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time1` bigint(14) DEFAULT NULL,
  `end_time1` bigint(14) DEFAULT NULL,
  `oper_status1` int(2) DEFAULT '0',
  `start_time2` bigint(14) DEFAULT NULL,
  `end_time2` bigint(14) DEFAULT NULL,
  `oper_status2` int(2) DEFAULT '1',
  `start_time3` bigint(14) DEFAULT NULL,
  `end_time3` bigint(14) DEFAULT NULL,
  `oper_status3` int(2) DEFAULT '0',
  `shop_hour_type` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `is_pub` int(11) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*数据表 `t_shop_hour` 的数据*/

insert into `t_shop_hour` values 

(1,0,7,0,0,23,1,23,24,0,1,0,NULL,NULL,1,'2014.11.04','remove',0),

(4,0,7,0,7,23,1,23,24,0,2,0,'2014-10-23 15:15:15','2014-10-24 15:15:15',1,'2014.11.04',NULL,0),

(21,0,7,0,7,23,1,23,24,0,2,0,'2014-11-25 11:47:33','2014-11-26 11:47:36',0,'2014.11.04','add',0);

/*数据表 `t_site_info` 的表结构*/

DROP TABLE IF EXISTS `t_site_info`;

CREATE TABLE `t_site_info` (
  `site_code` varchar(255) NOT NULL,
  `monitor_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reg_status` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `legal_represent` varchar(255) DEFAULT NULL,
  `principal` varchar(255) DEFAULT NULL,
  `principal_tel` varchar(255) DEFAULT NULL,
  `administrator` varchar(255) DEFAULT NULL,
  `admin_tel` varchar(255) DEFAULT NULL,
  `terminal_num` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator` varchar(255) DEFAULT NULL,
  `hour_ver` int(11) DEFAULT NULL,
  `url_ver` int(11) DEFAULT NULL,
  `prog_ver` int(11) DEFAULT NULL,
  `special_ver` int(11) DEFAULT NULL,
  PRIMARY KEY (`site_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_site_info` 的数据*/

insert into `t_site_info` values 

('3101010001','310101','aa11','0','aaaa','aa','aa','aa','aa',NULL,'aa','185','2014-11-03 20:13:32','admin',NULL,NULL,NULL,NULL,NULL,NULL),

('3101010002','310101','bb11','0','bb','bb','bb','bb','bb','bb','bb','135','2014-10-23 15:15:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL),

('3101010003','310101','cccccccccc','0','???????324?','020102','??','??','152463874654','??','18235647852','234','2014-11-03 19:40:00','admin',NULL,NULL,NULL,NULL,NULL,NULL);

/*数据表 `t_site_live` 的表结构*/

DROP TABLE IF EXISTS `t_site_live`;

CREATE TABLE `t_site_live` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(255) DEFAULT NULL,
  `connect_term` bigint(16) DEFAULT NULL,
  `active_customer_count` bigint(16) DEFAULT NULL,
  `run_status` int(11) DEFAULT NULL,
  `total_alarm` bigint(16) DEFAULT NULL,
  `total_punish` bigint(16) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_site_live` 的数据*/

insert into `t_site_live` values 

(1,'3101010001',148,140,0,12,3,'2014-10-23 15:15:15'),

(2,'3101010002',230,180,0,42,14,'2014-10-23 15:15:15');

/*数据表 `t_site_punish` 的表结构*/

DROP TABLE IF EXISTS `t_site_punish`;

CREATE TABLE `t_site_punish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `site_code` varchar(255) DEFAULT NULL,
  `site_name` varchar(255) DEFAULT NULL,
  `punish_time` datetime DEFAULT NULL,
  `punish_reason` int(11) DEFAULT NULL,
  `punish_type` int(11) DEFAULT NULL,
  `punish_person` varchar(20) DEFAULT NULL,
  `permit_person` varchar(20) DEFAULT NULL,
  `punish_org` varchar(255) DEFAULT NULL,
  `punish_result` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_site_punish` 的数据*/

insert into `t_site_punish` values 

(1,'11','11','2014-10-23 15:15:15',11,11,'11','11','11',11,'2014-10-23 15:15:15','11'),

(2,'22','22','2014-10-23 15:15:15',22,22,'2','22','22',22,'2014-10-23 15:15:15','22');

/*数据表 `t_site_terminal_log` 的表结构*/

DROP TABLE IF EXISTS `t_site_terminal_log`;

CREATE TABLE `t_site_terminal_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SITE_CODE` varchar(255) DEFAULT NULL,
  `MONITOR_CODE` varchar(255) DEFAULT NULL,
  `HOST_IP` varchar(255) DEFAULT NULL,
  `ACCOUNT_ID` varchar(255) DEFAULT NULL,
  `CUSTOMER_NAME` varchar(255) DEFAULT NULL,
  `CUSTOMER_ID` varchar(255) DEFAULT NULL,
  `CUSTOMER_ID_TYPE` int(11) DEFAULT NULL,
  `ONLINE_TIME` datetime NOT NULL,
  `OFFLINE_TIME` datetime DEFAULT NULL,
  `TIME_SPAN` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*数据表 `t_site_terminal_log` 的数据*/

insert into `t_site_terminal_log` values 

(1,'22','44','','','','',0,'2014-10-23 15:15:15',NULL,0),

(2,'3101010001','310101','123456789','1001','pengyue','123456789456789',1,'2014-10-23 15:15:15',NULL,NULL),

(3,'3101010001','310101','978654321','1002','zhang','987654321987654',1,'2014-10-23 15:15:15',NULL,NULL),

(4,'3101010001','310101','654987312','1003','weq','21321654987464654',1,'2014-10-23 15:15:15',NULL,NULL);

/*数据表 `t_special_customer` 的表结构*/

DROP TABLE IF EXISTS `t_special_customer`;

CREATE TABLE `t_special_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `cert_type` int(11) DEFAULT NULL,
  `cert_id` varchar(20) DEFAULT NULL,
  `nationality` varchar(20) DEFAULT NULL,
  `alarm_rank` int(11) DEFAULT NULL,
  `is_pub` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  `version` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `delete_flag` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*数据表 `t_special_customer` 的数据*/

insert into `t_special_customer` values 

(4,'cindy',1,'1111111111','??',1,1,'2014-10-23 15:15:15',NULL,'2014-11-05 20:00:45','admin','1.0.1','edit',0),

(5,'jack1',1,'1111111','??',3,0,'2014-10-23 15:15:15',NULL,'2014-11-04 13:38:21','admin','1.0.0','edit',0),

(6,'111',1,'1111111','11',1,0,'2014-11-04 13:24:27','admin',NULL,NULL,'1.0.0','remove',1);

/*数据表 `t_stat_day_alarm` 的表结构*/

DROP TABLE IF EXISTS `t_stat_day_alarm`;

CREATE TABLE `t_stat_day_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_day` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `alarm_count` int(11) DEFAULT NULL,
  `punish_count` int(11) DEFAULT NULL,
  `customer_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_day_alarm` 的数据*/

/*数据表 `t_stat_day_prog` 的表结构*/

DROP TABLE IF EXISTS `t_stat_day_prog`;

CREATE TABLE `t_stat_day_prog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_day` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `prog_name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_day_prog` 的数据*/

/*数据表 `t_stat_day_url` 的表结构*/

DROP TABLE IF EXISTS `t_stat_day_url`;

CREATE TABLE `t_stat_day_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_day` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `access_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_day_url` 的数据*/

/*数据表 `t_stat_month_alarm` 的表结构*/

DROP TABLE IF EXISTS `t_stat_month_alarm`;

CREATE TABLE `t_stat_month_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `alarm_count` int(11) DEFAULT NULL,
  `punish_count` int(11) DEFAULT NULL,
  `customer_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_month_alarm` 的数据*/

/*数据表 `t_stat_month_prog` 的表结构*/

DROP TABLE IF EXISTS `t_stat_month_prog`;

CREATE TABLE `t_stat_month_prog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `prog_name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_month_prog` 的数据*/

/*数据表 `t_stat_month_url` 的表结构*/

DROP TABLE IF EXISTS `t_stat_month_url`;

CREATE TABLE `t_stat_month_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `access_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_month_url` 的数据*/

/*数据表 `t_stat_year_alarm` 的表结构*/

DROP TABLE IF EXISTS `t_stat_year_alarm`;

CREATE TABLE `t_stat_year_alarm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `alarm_count` int(11) DEFAULT NULL,
  `punish_count` int(11) DEFAULT NULL,
  `customer_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_year_alarm` 的数据*/

/*数据表 `t_stat_year_prog` 的表结构*/

DROP TABLE IF EXISTS `t_stat_year_prog`;

CREATE TABLE `t_stat_year_prog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `prog_name` varchar(255) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_year_prog` 的数据*/

/*数据表 `t_stat_year_url` 的表结构*/

DROP TABLE IF EXISTS `t_stat_year_url`;

CREATE TABLE `t_stat_year_url` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_year` int(11) DEFAULT NULL,
  `sta_month` int(11) DEFAULT NULL,
  `sta_time` datetime DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `access_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_stat_year_url` 的数据*/

/*数据表 `t_url_history` 的表结构*/

DROP TABLE IF EXISTS `t_url_history`;

CREATE TABLE `t_url_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `site_code` varchar(20) DEFAULT NULL,
  `monitor_code` varchar(20) DEFAULT NULL,
  `province_code` varchar(20) DEFAULT NULL,
  `city_code` varchar(20) DEFAULT NULL,
  `county_code` varchar(20) DEFAULT NULL,
  `host_ip` varchar(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `url_domain` varchar(20) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `customer_type` int(11) DEFAULT NULL,
  `customer_name` varchar(20) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  `is_block` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*数据表 `t_url_history` 的数据*/

insert into `t_url_history` values 

(1,2003130,'3001','3010','020','020','03','172.16.51.134','www.baidu.com','baidu','2014-10-23 15:15:15',1,'charc','1578234847535896',1),

(2,200135,'3001','3010','020','020','03','172.16.51.154','www.weibo.com','sina','2014-10-23 15:15:15',1,'cindy','11427722485442',1);

/*数据表 `t_user` 的表结构*/

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `common_name` varchar(255) NOT NULL,
  `id_card` varchar(20) DEFAULT NULL,
  `mobile` varchar(18) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `lastlogin` datetime DEFAULT NULL,
  `fail_count` int(2) NOT NULL DEFAULT '0',
  `status` bit(1) NOT NULL DEFAULT b'0',
  `salt` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

/*数据表 `t_user` 的数据*/

insert into `t_user` values 

(1,'admin','b43e3c94b84587accfe71f5621ebe8835fc7cbb7','cindy','123456789123456','12345678912','zhang@sg.com','2014-10-23 15:15:15','2014-10-23 15:15:15',0,'','5a02c8439ccd8622'),

(2,'pengyue','a9ec3f6f51c4eed8704044da9b2d759bf980292b','jack','123456789123456','123456798456','peng@xg.com','2014-10-23 15:15:15','2014-10-23 15:15:15',0,'','0b0c4e3d650930ae'),

(4,'root','111','root','123','123','123','2014-10-23 15:15:15','2014-10-23 15:15:15',0,'','10'),

(50,'ddd','ddd','ddd','','','','2014-10-23 15:15:15','2014-10-23 15:15:15',0,'','10'),

(55,'115','f637dc811e8f26e7','115','115','115','115','2014-11-05 08:57:31',NULL,0,'','f94389b666b7460f'),

(57,'test116','7962736040fb71272259d03fb3ba98fa24eb10c3','test116','1523647854','152364785411','1523647854@126.com','2014-11-06 13:06:27',NULL,0,'','61ea048da9b96717');

/*数据表 `t_user_role` 的表结构*/

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*数据表 `t_user_role` 的数据*/

insert into `t_user_role` values 

(1,1,'2014-10-23 15:15:15'),

(1,2,'2014-10-23 15:15:15'),

(2,4,'2014-10-23 15:15:15'),

(4,4,'2014-11-04 20:12:45'),

(50,4,'2014-10-23 15:15:15'),

(55,18,'2014-11-05 08:57:51'),

(57,16,'2014-11-06 13:09:47');
