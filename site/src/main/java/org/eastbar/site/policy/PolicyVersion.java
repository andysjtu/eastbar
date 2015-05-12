package org.eastbar.site.policy;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by andysjtu on 2015/5/11.
 */
//@Entity
//@Table(name="policy_version")
public class PolicyVersion implements Serializable{

    private int urlVersion;
    private int pgVersion;
    private int spmVersion;
    private int kwVersion;

    public int getPgVersion() {
        return pgVersion;
    }

    public void setPgVersion(int pgVersion) {
        this.pgVersion = pgVersion;
    }

    public int getSpmVersion() {
        return spmVersion;
    }

    public void setSpmVersion(int spmVersion) {
        this.spmVersion = spmVersion;
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
}
