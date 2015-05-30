package org.eastbar.codec;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by andysjtu on 2015/5/18.
 */
public class UserInfo {
    private String hostIp;
    private String account;
    private String name;
    private String id;
    private String idType;
    private String authOrg;
    private String loginTime;
    private String logoutTime;
    private String nation;
    private String desc;

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

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
                ", hostIp='" + hostIp + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", idType='" + idType + '\'' +
                ", authOrg='" + authOrg + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                ", nation='" + nation + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static class T{
        int verNum;
        String version;
        private List<String> xx= Lists.newArrayList();

        public List<String> getXx() {
            return xx;
        }

        public void setXx(List<String> xx) {
            this.xx = xx;
        }

        public int getVerNum() {
            return verNum;
        }

        public void setVerNum(int verNum) {
            this.verNum = verNum;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static void main(String[] args) {
        T t = new T();
        System.out.println(JsonUtil.toJson(t));
    }
}
