package org.eastbar.codec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class CenterInitReq extends SocketMsg{
    private  List<SiteReport> siteReportList;

    public CenterInitReq(List<SiteReport> siteReportList) {
        this.siteReportList = siteReportList;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.INIT_CONN.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(siteReportList));
    }

    public CenterInitReq(SocketMsg socketMsg){
        super(socketMsg);
        parseContent(socketMsg.data().content());
    }
    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.siteReportList = JsonUtil.fromJson(new TypeReference<List<SiteReport>>() {}, content);
    }

    protected ByteBuf toByteBuf(List<SiteReport> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public List<SiteReport> getSiteReportList() {
        return siteReportList;
    }

    public void setSiteReportList(List<SiteReport> siteReportList) {
        this.siteReportList = siteReportList;
    }
}
