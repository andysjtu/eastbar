package org.eastbar.codec;

import io.netty.buffer.ByteBuf;

/**
 * Created by AndySJTU on 2015/5/25.
 */
public class CaptureResp extends SocketMsg {
    private short recMessageId;
    private short recMessageType;
    private byte[] content;

    public CaptureResp(SocketMsg oldMsg) {
        super(oldMsg);
        ByteBuf buf = oldMsg.data().content().duplicate();
        recMessageId = buf.readShort();
        recMessageType = buf.readShort();
        content = new byte[buf.readableBytes()];
        buf.readBytes(content);
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
}
