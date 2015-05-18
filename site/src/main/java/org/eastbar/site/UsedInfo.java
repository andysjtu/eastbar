package org.eastbar.site;

/**
 * Created by andysjtu on 2015/5/18.
 */
public class UsedInfo {
    private String account;
    private String name;
    private String id;
    private String idType;
    private String authOrg;
    private String loginTime;
    private String logoutTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAuthOrg() {
        return authOrg;
    }

    public void setAuthOrg(String authOrg) {
        this.authOrg = authOrg;
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
        return "UsedInfo{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", idType='" + idType + '\'' +
                ", authOrg='" + authOrg + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                '}';
    }
}
