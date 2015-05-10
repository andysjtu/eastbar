package org.eastbar.codec;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class ClientInitResp extends SocketMsg {
    public final static int MESSAGE_TYPE = 0x3001;
    private short recMessageId;
    private short recMessageType;
    private String userId;
    private String idtype;
    private String userName;
    private int specailMode;
    private String userAccount;

    public short getRecMessageId() {
        return recMessageId;
    }

    public void setRecMessageId(short recMessageId) {
        this.recMessageId = recMessageId;
    }

    public short getRecMessageType() {
        return recMessageType;
    }

    public void setRecMessageType(short recMessageType) {
        this.recMessageType = recMessageType;
    }

    public ClientInitResp() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        //TODO
    }
}
