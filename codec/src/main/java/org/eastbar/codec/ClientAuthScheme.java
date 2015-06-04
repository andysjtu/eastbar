package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/6.
 */
public class ClientAuthScheme {
    private String version;
    private String macAddress;
    private String ip;
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }



    @Override
    public String toString() {
        return "ClientAuthScheme{" +
                "version='" + version + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", ip='" + ip + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
