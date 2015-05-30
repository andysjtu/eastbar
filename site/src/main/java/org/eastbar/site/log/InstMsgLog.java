package org.eastbar.site.log;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "intmsg_log")
public class InstMsgLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
