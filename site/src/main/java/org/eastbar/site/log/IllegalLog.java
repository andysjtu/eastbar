package org.eastbar.site.log;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/5/29.
 */
@Entity
@Table(name = "illegal_log")
public class IllegalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
