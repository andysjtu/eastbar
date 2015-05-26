package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;

import java.util.Arrays;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class SocketMsg implements ReferenceCounted {
    private byte msgAttr;
    private byte version = ProtocolVersion.Version.byteValue();
    private short messageType;
    private short messageId;
    private IpV4 host;

    private ByteBufHolder data;

    public SocketMsg() {
    }

    public SocketMsg(SocketMsg oldMsg) {
        this.msgAttr = oldMsg.getMsgAttr();
        this.version = oldMsg.getVersion();
        this.messageType = oldMsg.getMessageType();
        this.messageId = oldMsg.getMessageId();
        this.host = oldMsg.getHost();
        this.data = oldMsg.data();
    }

    public byte getMsgAttr() {
        return msgAttr;
    }

    public void setMsgAttr(byte msgAttr) {
        this.msgAttr = msgAttr;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public void setVersion(ProtocolVersion version) {
        this.version = version.byteValue();
    }

    public short getMessageType() {
        return messageType;
    }

    public void setMessageType(short messageType) {
        this.messageType = messageType;
    }

    public short getMessageId() {
        return messageId;
    }

    public void setMessageId(short messageId) {
        this.messageId = messageId;
    }

    public IpV4 getHost() {
        return host;
    }

    public void setHost(IpV4 host) {
        this.host = host;
    }

    public void changeSiteToCenterAttr() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
    }

    public void changeSiteToClientAttr() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
    }


    @Override
    public int refCnt() {
        return data.refCnt();
    }

    @Override
    public SocketMsg retain() {
        if (data != null)
            data.retain();
        return this;
    }

    @Override
    public SocketMsg retain(int increment) {
        if (data != null)
            data.retain(increment);
        return this;
    }

    @Override
    public boolean release() {
        if (data != null) {
            return data.release();
        }
        return false;
    }


    @Override
    public boolean release(int decrement) {
        if (data != null)
            return data.release(decrement);
        return false;
    }

    protected void parseContent(ByteBuf content) {
    }

    public void setContent(ByteBuf buf) {
        data = new DefaultByteBufHolder(buf);
    }

    public void readByteBuf(ByteBuf buf) {
        int readable = buf.readableBytes();
        if (readable < 14) {
            throw new RuntimeException("byteBuf readable length is not enought");
        }
        msgAttr = buf.readByte();
        version = buf.readByte();
        messageType = buf.readShort();
        messageId = buf.readShort();
        byte[] ipContent = new byte[4];
        buf.readBytes(ipContent);
        host = new IpV4(ipContent);
        int length = buf.readInt();

        if (length > 0) {
            data = new DefaultByteBufHolder(buf.readBytes(length));
        }

    }


    public void encodeAsByteBuf(ByteBuf buf) {
        buf.writeByte(msgAttr);
        buf.writeByte(version);
        buf.writeShort(messageType);
        buf.writeShort(messageId);
        buf.writeBytes(host.getContent());
        if (data == null) {
            buf.writeInt(0);
        } else {
            buf.writeInt(data.content().readableBytes());
            buf.writeBytes(data.content());
        }

    }

    ;


    public ByteBufHolder data() {
        return this.data;
    }

    public void data(ByteBuf buf) {
        if (buf != null)
            this.data = new DefaultByteBufHolder(buf);
    }

    @Override
    public String toString() {
        return "SocketMsg{" +
                "msgAttr=" + msgAttr +
                ", version=" + version +
                ", messageType=" + messageType +
                ", messageId=" + messageId +
                ", host=" + host.toRegularIpFormat() +
                '}';
    }

    public static void main(String[] args) {
        SiteBeatenEvent event = new SiteBeatenEvent();

        System.out.println("event is : " + event);
        IDGenerator.nextID();
        System.out.println("event is : " + event);

        ByteBuf buf = Unpooled.buffer(14);

        event.encodeAsByteBuf(buf);

        System.out.println(Arrays.toString(buf.array()));

    }


    public void setData(ByteBufHolder data) {
        this.data = data;
    }
}
