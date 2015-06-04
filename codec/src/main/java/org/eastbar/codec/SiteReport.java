package org.eastbar.codec;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AndySJTU on 2015/5/19.
 */
public class SiteReport implements Serializable {
    private String siteCode;
    private int urlVersion;
    private int prgVersion;
    private int kwVersion;
    private int smVersion;
    private boolean connected;

    public SiteReport() {
    }

    public int getKwVersion() {
        return kwVersion;
    }

    public void setKwVersion(int kwVersion) {
        this.kwVersion = kwVersion;
    }

    public int getPrgVersion() {
        return prgVersion;
    }

    public void setPrgVersion(int prgVersion) {
        this.prgVersion = prgVersion;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }


    public int getUrlVersion() {
        return urlVersion;
    }

    public void setUrlVersion(int urlVersion) {
        this.urlVersion = urlVersion;
    }

    public int getSmVersion() {
        return smVersion;
    }

    public void setSmVersion(int smVersion) {
        this.smVersion = smVersion;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteReport report = (SiteReport) o;

        return !(siteCode != null ? !siteCode.equals(report.siteCode) : report.siteCode != null);

    }

    @Override
    public int hashCode() {
        return siteCode != null ? siteCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
