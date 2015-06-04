package org.eastbar.codec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.List;
import java.util.Map;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class CenterInitReq extends SocketMsg {
    private Map<SiteReport, List<TermReport>> reportListMap;

    public CenterInitReq(Map<SiteReport, List<TermReport>> siteTermReports) {
        this.reportListMap = siteTermReports;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.CENTER_INIT_CONN.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(reportListMap));
    }

    public CenterInitReq(SocketMsg socketMsg) {
        super(socketMsg);
        parseContent(socketMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.reportListMap = JsonUtil.fromJson(new TypeReference<Map<SiteReport,List<TermReport>>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(Map<SiteReport, List<TermReport>> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public Map<SiteReport, List<TermReport>> getSiteTermReports() {
        return reportListMap;
    }

    public static void main(String[] args) {
        Map<SiteReport, List<TermReport>> t = Maps.newHashMap();
        SiteReport report = new SiteReport();
        System.out.println(JsonUtil.toJson(report));
        TermReport termReport = new TermReport();
        t.put(report, Lists.newArrayList(termReport));
        String content = JsonUtil.toJson(t);

        System.out.println(content);

        Map<SiteReport, List<TermReport>> t2 = JsonUtil.fromJson(new TypeReference<Map<SiteReport, List<TermReport>>>() {
        },content.getBytes(Charsets.UTF_8));
        System.out.println("t2 is : "+t2);

    }

}
