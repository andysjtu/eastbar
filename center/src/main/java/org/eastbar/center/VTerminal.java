package org.eastbar.center;

/**
 * Created by andysjtu on 2015/5/12.
 */
public class VTerminal {
    private final String terminalIP;
    private final String siteCode;

    private Status status;

    private String hostIp;
    private String account;
    private String name;
    private String idType;
    private String id;
    private String authOrg;

    private String macAddress;
    private String os;
    private String version;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTerminalIP() {
        return terminalIP;
    }

    public VTerminal(String terminalIP, String siteCode) {
        this.terminalIP = terminalIP;
        this.siteCode = siteCode;
    }

    private void changeStatus(Status status){
        this.status = status;
    }

    public void login(){
        this.status = Status.login;
    }
    public void logout(){
        this.status = Status.logout;
    }

    public void offline(){
        this.status = Status.offline;
    }

    public void online(){
        this.status = Status.online;
    }



    public static enum Status {
        offline, login, online, logout;
    }


}
