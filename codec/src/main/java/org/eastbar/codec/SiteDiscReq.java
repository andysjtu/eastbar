package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.List;

/**
 * Created by andyliang on 6/29/15.
 */
public class SiteDiscReq extends SocketMsg {
    private SiteReport siteReport;

    public SiteDiscReq(SiteReport siteReport) {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.SITE_DISC_CONN.shortValue());
        setHost(IpV4.localIpV4());
        this.siteReport = siteReport;
        data(toByteBuf(siteReport));

    }

    public SiteDiscReq(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.siteReport = JsonUtil.fromJson(SiteReport.class, content);
    }

    protected ByteBuf toByteBuf(SiteReport report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public SiteReport getSiteReport() {
        return this.siteReport;
    }

}
