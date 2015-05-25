package org.eastbar.site;

/**
 * Created by andysjtu on 2015/5/18.
 */
public class TerminalInfo {
    private String version;
    private String os;
    private String macAddress;
    private String ipAddress;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
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

    @Override
    public String toString() {
        return "TerminalInfo{" +
                "ipAddress='" + ipAddress + '\'' +
                ", version='" + version + '\'' +
                ", os='" + os + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
