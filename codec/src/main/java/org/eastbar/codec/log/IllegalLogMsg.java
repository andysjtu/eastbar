package org.eastbar.codec.log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.comm.log.entity.IllegalLog;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class IllegalLogMsg extends SocketMsg{
    private List<IllegalLog> illegalLogMsgList;

    public IllegalLogMsg(List<IllegalLog> illegalLogMsgList) {
        this.illegalLogMsgList = illegalLogMsgList;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.ILLEGAL_LOG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(illegalLogMsgList));
    }

    public IllegalLogMsg(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.illegalLogMsgList = JsonUtil.fromJson(new TypeReference<List<IllegalLog>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<IllegalLog> logs) {
        String content = JsonUtil.toJson(logs);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<IllegalLog> getLogs() {
        return illegalLogMsgList;
    }

}
