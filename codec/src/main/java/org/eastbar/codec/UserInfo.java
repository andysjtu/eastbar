package org.eastbar.codec;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/18.
 */
public class UserInfo {
    private String ip;
    private String account;
    private String name;
    private String id;
    private String idType;
    private String authOrg;
    private String loginTime;
    private String logoutTime;
    private String nation;


    public UserInfo(UserInfo loginEvent) {
        DozerUtil.copyProperties(loginEvent, this);
    }

    public UserInfo() {
    }

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "account='" + account + '\'' +
                ", ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", idType='" + idType + '\'' +
                ", authOrg='" + authOrg + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                ", nation='" + nation + '\'' +
                '}';
    }


    public boolean isSameLogoutInfo(UserInfo o) {
        if (this == o) return true;
        if (o == null) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != null ? !id.equals(userInfo.id) : userInfo.id != null) return false;
        if (loginTime != null ? !loginTime.equals(userInfo.loginTime) : userInfo.loginTime != null) return false;
        return !(logoutTime != null ? !logoutTime.equals(userInfo.logoutTime) : userInfo.logoutTime != null);

    }


    public boolean isSameLoginInfo(UserInfo o) {
        if (this == o) return true;
        if (o == null) return false;

        UserInfo userInfo = (UserInfo) o;

        if (id != null ? !id.equals(userInfo.id) : userInfo.id != null) return false;
        if (loginTime != null ? !loginTime.equals(userInfo.loginTime) : userInfo.loginTime != null) return false;
        return true;
    }


}
