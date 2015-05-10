package org.eastbar.site.host.codec;

/**
 * Created by andysjtu on 2015/5/7.
 */
public class AuthResponse {
    private String version;
    private String idType;
    private String userId;
    private String userName;
    private boolean specialModel;
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

    public boolean isSpecialModel() {
        return specialModel;
    }

    public void setSpecialModel(boolean specialModel) {
        this.specialModel = specialModel;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
