package org.eastbar.log2db.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name="t_inst_msg_history")
public class SiteInstMsgLog  {
    private String siteCode;
    @Column(name="customer_id")
    private String customerId;
    
    @Column(name="customer_name")
    private String customerName;
    
    @Column(name = "host_ip")
    private String hostIp;
    
    @Column(name = "access_time")
    private Timestamp recordTime;
    
    @Column(name = "is_block")
    private boolean blocked = false;
    
    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "prog_type")
    private String progType;
    
    @Column(name = "prog_account")
    private String progAccount;
    
    @Column(name = "start_time")
    private String startTime;
    
    @Column(name = "end_time")
    private String endTime;
}
