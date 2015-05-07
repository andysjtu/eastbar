package org.eastbar.site.host.codec;

/**
 * Created by andysjtu on 2015/5/6.
 */
public class AuthSchme {
    private String version;
    private String macAddress;
    private String ipAddress;
    private String os;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "AuthSchme{" +
                "version='" + version + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
