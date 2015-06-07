package org.eastbar.codec.alert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.comm.alert.entity.ProgBlockAlert;
import org.eastbar.comm.alert.entity.UrlBlockAlert;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class UrlBlockAlertMsg extends SocketMsg {
    private List<UrlBlockAlert> alerts;

    public UrlBlockAlertMsg(List<UrlBlockAlert> alerts) {
        this.alerts = alerts;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.URL_ALERT.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(alerts));
    }

    public UrlBlockAlertMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    public List<UrlBlockAlert> getAlerts() {
        return alerts;
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.alerts = JsonUtil.fromJson(new TypeReference<List<UrlBlockAlert>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<UrlBlockAlert> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }
}
