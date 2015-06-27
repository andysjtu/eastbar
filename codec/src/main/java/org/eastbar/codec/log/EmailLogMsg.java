package org.eastbar.codec.log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.net.log.entity.EmailLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class EmailLogMsg extends SocketMsg{
    private List<EmailLog> logs;

    public EmailLogMsg(List<EmailLog> logs) {
        this.logs = logs;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.EMAIL_LOG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(logs));
    }

    public EmailLogMsg(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.logs = JsonUtil.fromJson(new TypeReference<List<EmailLog>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<EmailLog> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<EmailLog> getLogs() {
        return logs;
    }
}
