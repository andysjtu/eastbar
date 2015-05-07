package org.eastbar.site.host.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.DefaultByteBufHolder;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class SocketMsg {
    private byte msgAttr;
    private byte version;
    private short messageType;
    private short messageId;
    private IpV4 host;

    private DefaultByteBufHolder data;


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
}
