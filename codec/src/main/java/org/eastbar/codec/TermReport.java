package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/19.
 */
public class TermReport {
    private String hostIp;
    private String account;
    private String name;
    private String idType;
    private String id;
    private String authOrg;

    private String os;
    private String version;
    private String macAddress;

    private String loginTime;
    private String logoutTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAuthOrg() {
        return authOrg;
    }

    public void setAuthOrg(String authOrg) {
        this.authOrg = authOrg;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    @Override
    public String toString() {
        return "TermReport{" +
                "account='" + account + '\'' +
                ", hostIp='" + hostIp + '\'' +
                ", name='" + name + '\'' +
                ", idType='" + idType + '\'' +
                ", id='" + id + '\'' +
                ", authOrg='" + authOrg + '\'' +
                ", os='" + os + '\'' +
                ", version='" + version + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                '}';
    }
}
