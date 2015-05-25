package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/7.
 */
public class ClientAuthResp {
    private String version;
    private String idType;
    private String id;
    private String name;
    private boolean specialMode;
    private String account;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSpecialMode() {
        return specialMode;
    }

    public void setSpecialMode(boolean specialMode) {
        this.specialMode = specialMode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String toString(){
        String str = "Version:" + this.version + "\r\n" + "IdType:"
                + this.idType + "\r\n" + "UserId:" + this.id + "\r\n"
                + "UserName:" + this.name + "\r\n" + "SPECIAL_MODE:"
                + (this.specialMode?"1":"0") + "\r\n" + "UserAccount:"
                + this.account + "\r\n";
       return str;
    }
}
