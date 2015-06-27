package org.eastbar.codec.alert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.net.alert.entity.GeneralAlert;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class GeneralAlertMsg extends SocketMsg {
    private List<GeneralAlert> alerts;

    public GeneralAlertMsg(List<GeneralAlert> alerts) {
        this.alerts = alerts;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.GENERAL_ALERT.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(alerts));
    }

    public GeneralAlertMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    public List<GeneralAlert> getAlerts() {
        return alerts;
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.alerts = JsonUtil.fromJson(new TypeReference<List<GeneralAlert>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<GeneralAlert> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }
}
