package org.eastbar.codec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by AndySJTU on 2015/5/27.
 */
public class TermReportMsg extends SocketMsg {
    public final static Logger logger= LoggerFactory.getLogger(TermReportMsg.class);

    private List<TermReport> report;

    public TermReportMsg(List<TermReport> report) {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.TERM_STATUS.shortValue());
        setHost(IpV4.localIpV4());
        this.report = report;
        data(toByteBuf(report));
    }

    private ByteBuf toByteBuf(List<TermReport> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public TermReportMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        logger.info("接受到的字符串是 : "+new String(content));
        this.report = JsonUtil.fromJson(new TypeReference<List<TermReport>>() {
        }, content);
    }

    public List<TermReport> getReport() {
        return report;
    }

    public void setReport(List<TermReport> report) {
        this.report = report;
    }

    public static void main(String[] args) {
        List<TermReport> termReports = Lists.newArrayList(new TermReport());
        System.out.println(JsonUtil.toJson(termReports));
    }
}
