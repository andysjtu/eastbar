package org.eastbar.site.policy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by andysjtu on 2015/5/11.
 */
@Entity
@Table(name="policy_version")
public class SitePolicyVersion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private int urlVersion;
    private int prgVersion;
    private int kwVersion;
    private int smVersion;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPrgVersion() {
        return prgVersion;
    }

    public void setPrgVersion(int prgVersion) {
        this.prgVersion = prgVersion;
    }

    public int getSmVersion() {
        return smVersion;
    }

    public void setSmVersion(int smVersion) {
        this.smVersion = smVersion;
    }

    public int getKwVersion() {
        return kwVersion;
    }

    public void setKwVersion(int kwVersion) {
        this.kwVersion = kwVersion;
    }

    public int getUrlVersion() {

        return urlVersion;
    }

    public void setUrlVersion(int urlVersion) {
        this.urlVersion = urlVersion;
    }


    @Override
    public String toString() {
        return "SitePolicyVersion{" +
                "id=" + id +
                ", urlVersion=" + urlVersion +
                ", prgVersion=" + prgVersion +
                ", kwVersion=" + kwVersion +
                ", smVersion=" + smVersion +
                '}';
    }
}
