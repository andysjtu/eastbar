package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class SocketMsg {
    private byte msgAttr;
    private byte version=ProtocolVersion.Version.byteValue();
    private short messageType;
    private short messageId;
    private IpV4 host;


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

    public void setVersion(ProtocolVersion version){
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

    private DefaultByteBufHolder data=null;

    public void parse(){
        try{
            parseContent(data.content());
        }finally {
            data.release();
        }
    }

    protected void parseContent(ByteBuf content) {
    }

    public void setContent(ByteBuf buf){
        data = new DefaultByteBufHolder(buf);
    }

    public void readByteBuf(ByteBuf buf){
       int readable = buf.readableBytes();
        if(readable<14){
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

        if(length>0){
            data = new DefaultByteBufHolder(buf.readBytes(length));
        }

    }


    public  void encodeAsByteBuf(ByteBuf buf){
        buf.writeByte(msgAttr);
        buf.writeByte(version);
        buf.writeShort(messageType);
        buf.writeShort(messageId);
        buf.writeBytes(host.getContent());
        if(data ==null){
            buf.writeInt(0);
        }
        else{
            buf.writeInt(data.content().readableBytes());
            buf.writeBytes(data.content());
        }

    };


    public ByteBufHolder data(){
        return this.data;
    }

    public void data(ByteBuf buf){
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
                ", data=" + data.content().toString(Charsets.UTF_8) +
                '}';
    }


}
