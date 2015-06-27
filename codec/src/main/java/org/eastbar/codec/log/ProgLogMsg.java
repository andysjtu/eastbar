package org.eastbar.codec.log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.net.log.entity.PrgLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class ProgLogMsg extends SocketMsg{
    private List<PrgLog> logs;

    public ProgLogMsg(List<PrgLog> urlLogs) {
        this.logs = urlLogs;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.PROG_MSG_LOG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(urlLogs));
    }

    public ProgLogMsg(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }
    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        System.out.println("收到的内容是 :"+new String(content));
        this.logs = JsonUtil.fromJson(new TypeReference<List<PrgLog>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<PrgLog> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<PrgLog> getLogs() {
        return logs;
    }
}
