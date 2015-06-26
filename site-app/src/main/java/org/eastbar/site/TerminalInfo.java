package org.eastbar.site;

/**
 * Created by andysjtu on 2015/5/18.
 */
public class TerminalInfo {
    private String ip;
    private String version;
    private String os;
    private String macAddress;

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

    @Override
    public String toString() {
        return "TerminalInfo{" +
                "ip='" + ip + '\'' +
                ", version='" + version + '\'' +
                ", os='" + os + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
