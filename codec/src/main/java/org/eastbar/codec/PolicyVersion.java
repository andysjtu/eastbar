package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class PolicyVersion {
    private int urlVersion;
    private int prgVersion;
    private int kwVersion;
    private int smVersion;

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

    public int getSmVersion() {
        return smVersion;
    }

    public void setSmVersion(int smVersion) {
        this.smVersion = smVersion;
    }

    public int getUrlVersion() {
        return urlVersion;
    }

    public void setUrlVersion(int urlVersion) {
        this.urlVersion = urlVersion;
    }
}
