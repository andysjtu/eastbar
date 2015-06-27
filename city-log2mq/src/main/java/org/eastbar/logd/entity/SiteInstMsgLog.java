package org.eastbar.logd.entity;

import org.eastbar.net.EntityObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name="t_inst_msg_history")
public class SiteInstMsgLog extends EntityObject {
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

    private String progType;
    private String progAccount;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
}
