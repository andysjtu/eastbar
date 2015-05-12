package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/7.
 */
public class ClientAuthResp {
    private String version;
    private String idType;
    private String userId;
    private String userName;
    private boolean specialMode;
    private String userAccount;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSpecialMode() {
        return specialMode;
    }

    public void setSpecialMode(boolean specialMode) {
        this.specialMode = specialMode;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String toString(){
        String str = "Version:" + this.version + "\r\n" + "IdType:"
                + this.idType + "\r\n" + "UserId:" + this.userId + "\r\n"
                + "UserName:" + this.userName + "\r\n" + "SPECIAL_MODE:"
                + (this.specialMode?"1":"0") + "\r\n" + "UserAccount:"
                + this.userAccount + "\r\n";
       return str;
    }
}
