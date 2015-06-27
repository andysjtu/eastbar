package org.eastbar.net;

import javax.persistence.*;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@MappedSuperclass
public abstract class EntityObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "site_code")
    private String siteCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
}
