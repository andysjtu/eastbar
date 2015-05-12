package org.eastbar.site;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public class Terminal {

    private String username;
    private String id;
    private String idType;
    private String userAccount;
    private String clientVersion;
    private String os;
    private String macAddress;
    private String ipAddress;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
