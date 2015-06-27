package org.eastbar.codec.log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.net.log.entity.InstMsgLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class InstMsgLogMsg extends SocketMsg {
    private List<InstMsgLog> logs;

    public InstMsgLogMsg(List<InstMsgLog> logs) {
        this.logs = logs;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.INST_MSG_LOG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(logs));
    }

    public InstMsgLogMsg(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.logs = JsonUtil.fromJson(new TypeReference<List<InstMsgLog>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<InstMsgLog> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<InstMsgLog> getLogs() {
        return logs;
    }
}
