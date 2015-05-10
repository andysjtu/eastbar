package org.eastbar.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andysjtu on 2015/5/9.
 */
public abstract class GenResp extends SocketMsg {

    public static final short MESSAGE_TYPE = 0x0001;

    public abstract byte getMsgAttr();

    public GenResp(short recMessageId, short recMessageType, Status status) {
        buildHeader();
        buildContent(recMessageId, recMessageType, status);
    }

    protected void buildHeader() {
        setMsgAttr(getMsgAttr());
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
        Sucess((byte) 0), Failure((byte) 1), MessageError((byte) 2), Unspport((byte) 3), Processing((byte) 4);
        private final byte value;

        Status(byte value) {
            this.value = value;
        }


        public byte byteValue() {
            return value;
        }
    }

    public static class S2ClientGenResp extends GenResp {
        public S2ClientGenResp(short recMessageId, short recMessageType, Status status) {
            super(recMessageId, recMessageType, status);
        }

        @Override
        public byte getMsgAttr() {
            return MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue();
        }
    }

    public static class S2CenterGenResp extends GenResp {
        public S2CenterGenResp(short recMessageId, short recMessageType, Status status) {
            super(recMessageId, recMessageType, status);
        }

        @Override
        public byte getMsgAttr() {
            return MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue();
        }
    }
}
