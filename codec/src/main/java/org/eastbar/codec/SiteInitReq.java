package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.comm.Listener;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/19.
 */
public class SiteInitReq extends SocketMsg {
    private SiteInitContent content;

    public SiteInitReq(SiteReport siteReport, List<TermReport> termReports) {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.SITE_INIT_CONN.shortValue());
        setHost(IpV4.localIpV4());
        this.content = new SiteInitContent(siteReport, termReports);
        data(toByteBuf(content));

    }

    public SiteInitReq(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.content = JsonUtil.fromJson(SiteInitContent.class, content);
    }

    protected ByteBuf toByteBuf(SiteInitContent report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public SiteReport getSiteReport() {
        return this.content.getSiteReport();
    }

    public List<TermReport> getTermReport() {
        return content.getTermReportList();
    }

    public static class SiteInitContent {
        private SiteReport siteReport;
        private List<TermReport> termReportList;

        public SiteInitContent(SiteReport siteReport, List<TermReport> termReportList) {
            this.siteReport = siteReport;
            this.termReportList = termReportList;
        }

        public SiteInitContent() {
        }

        public SiteReport getSiteReport() {
            return siteReport;
        }

        public void setSiteReport(SiteReport siteReport) {
            this.siteReport = siteReport;
        }

        public List<TermReport> getTermReportList() {
            return termReportList;
        }

        public void setTermReportList(List<TermReport> termReportList) {
            this.termReportList = termReportList;
        }
    }
}
