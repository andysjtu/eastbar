package org.eastbar.logd.entity;

import org.eastbar.comm.EntityObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Entity
@Table(name="t_prog_history")
public class SiteProgLog extends EntityObject {
    private String customerId;
}
