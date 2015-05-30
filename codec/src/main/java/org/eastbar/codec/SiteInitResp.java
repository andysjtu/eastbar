package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public class SiteInitResp extends SocketMsg {
    private CenterNotice notice;

    public SiteInitResp(CenterNotice notice) {
        setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.INIT_CONN.shortValue());
        setHost(IpV4.localIpV4());
        this.notice = notice;
        this.data(Unpooled.wrappedBuffer(JsonUtil.toJson(notice).getBytes(Charsets.UTF_8)));
    }

    public SiteInitResp(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int length = buf.readableBytes();
        byte[] content = new byte[length];
        buf.readBytes(content);
        notice = JsonUtil.fromJson(CenterNotice.class, content);
    }

    public CenterNotice getNotice() {
        return notice;
    }

    public void setNotice(CenterNotice notice) {
        this.notice = notice;
    }
}
