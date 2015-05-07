package org.eastbar.site.host.codec;

import io.netty.buffer.ByteBuf;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public abstract class SocketMsg {
    private MsgAttr msgAttr;
    private ProtocolVersion version;
    private MessageType messageType;

    private short messageId;

    private IpV4 host;


    public abstract void encodeAsByteBuf(ByteBuf buf);
}
