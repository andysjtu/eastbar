package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class GenResp extends SocketMsg {

    public static final short MESSAGE_TYPE = 0x0001;

    private short recMessageId;
    private short recMessageType;
    private Status status;


    public GenResp(SocketMsg socketMsg) {
        super(socketMsg);
        ByteBuf buf = socketMsg.data().content().duplicate();
        recMessageId = buf.readShort();
        recMessageType = buf.readShort();
        status = Status.valueOf(buf.readByte());
    }

    public GenResp(byte attr,byte version,short recMessageId, short recMessageType, Status status) {
        buildHeader(attr,version);
        buildContent(recMessageId, recMessageType, status);
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    protected void buildHeader(byte attr,byte version) {
        setMsgAttr(attr);
        setVersion(ProtocolVersion.Version);
        setMessageType(MESSAGE_TYPE);
        setMessageId(IDGenerator.nextID());
        setHost(IpV4.localIpV4());
    }

    protected void buildContent(short recMessageId, short recMessageType, Status status) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(recMessageId);
        buf.writeShort(recMessageType);
        buf.writeByte(status.byteValue());
        data(buf);
    }

    public static enum Status {
        Sucess((byte) 0), Failure((byte) 1), MessageError((byte) 2), Unspport((byte) 3), Processing((byte) 4), UNKNOWN((byte) 0xff);
        private final byte value;

        public static Status valueOf(byte bv) {
            for (Status s : Status.values()) {
                if (s.value == bv) {
                    return s;
                }
            }
            return UNKNOWN;
        }

        Status(byte value) {
            this.value = value;
        }


        public byte byteValue() {
            return value;
        }
    }

    public static class S2ClientGenResp extends GenResp {
        public S2ClientGenResp(short recMessageId, short recMessageType, Status status) {
            super(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue(),ProtocolVersion.Version.byteValue(),recMessageId, recMessageType, status);
        }

//        @Override
//        public byte getMsgAttr() {
//            return MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue();
//        }
    }

    public static class S2CenterGenResp extends GenResp {
        public S2CenterGenResp(short recMessageId, short recMessageType, Status status) {
            super(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue(),ProtocolVersion.Version.byteValue(),recMessageId, recMessageType, status);
        }

//        @Override
//        public byte getMsgAttr() {
//            return MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue();
//        }
    }

    public static class Center2SGenResp extends GenResp {
        public Center2SGenResp(short recMessageId, short recMessageType, Status status) {
            super(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue(),ProtocolVersion.Version.byteValue(),recMessageId, recMessageType, status);
        }

//        @Override
//        public byte getMsgAttr() {
//            return MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue();
//        }
    }


}
