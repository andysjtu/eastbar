package org.eastbar.codec.log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.net.log.entity.UrlLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class UrlLogMsg extends SocketMsg {
    private List<UrlLog> urlLogs;

    public UrlLogMsg(List<UrlLog> urlLogs) {
        this.urlLogs = urlLogs;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.URL_LOG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(urlLogs));
    }

    public UrlLogMsg(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }
    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.urlLogs = JsonUtil.fromJson(new TypeReference<List<UrlLog>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<UrlLog> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<UrlLog> getUrlLogs() {
        return urlLogs;
    }


}
