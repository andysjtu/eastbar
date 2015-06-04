package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by AndySJTU on 2015/5/30.
 */
public class SiteReportMsg extends SocketMsg {
    private SiteReport report;

    public SiteReportMsg(SiteReport report) {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.POLICY_STATUS.shortValue());
        setHost(IpV4.localIpV4());
        this.report = report;
        data(toByteBuf(report));
    }

    private ByteBuf toByteBuf(SiteReport report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public SiteReportMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.report = JsonUtil.fromJson(SiteReport.class, content);
    }

    public SiteReport getReport() {
        return report;
    }

    public void setReport(SiteReport report) {
        this.report = report;
    }
}
