package org.eastbar.city;

import org.eastbar.codec.TermReport;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VTerminal {
    private final String terminalIP;
    private final String siteCode;


    private boolean online;
    private boolean connected;

    private String account;
    private String name;
    private String idType;
    private String id;
    private String authOrg;

    private String macAddress;
    private String os;
    private String version;

    private String loginTime;
    private String logoutTime;

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

    public String getSiteCode() {
        return siteCode;
    }



    public String getTerminalIP() {
        return terminalIP;
    }

    public VTerminal(String terminalIP, String siteCode) {
        this.terminalIP = terminalIP;
        this.siteCode = siteCode;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
