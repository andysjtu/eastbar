package org.eastbar.codec.alert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.comm.alert.entity.IllegalBlockAlert;
import org.eastbar.comm.log.entity.EmailLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class IllegalBlockAlertMsg extends SocketMsg{
    private List<IllegalBlockAlert> alerts;

    public IllegalBlockAlertMsg(List<IllegalBlockAlert> alerts) {
        this.alerts = alerts;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.ILLEGAL_ALERT.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(alerts));
    }

    public IllegalBlockAlertMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    public List<IllegalBlockAlert> getAlerts() {
        return alerts;
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.alerts = JsonUtil.fromJson(new TypeReference<List<IllegalBlockAlert>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<IllegalBlockAlert> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

}
