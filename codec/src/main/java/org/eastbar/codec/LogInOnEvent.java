package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * when terminal login,site will report to center
 * Created by AndySJTU on 2015/5/13.
 */
public class LogInOnEvent extends SocketMsg {
    private UserInfo info;

    public LogInOnEvent(UserInfo info, boolean login) {
        this.info = info;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        if (login) {
            setMessageType(SiteMsgType.LOGIN.shortValue());
        } else {
            setMessageType(SiteMsgType.LOGOUT.shortValue());
        }
        setHost(IpV4.localIpV4());
        data(convertContent(info));
    }

    private ByteBuf convertContent(UserInfo info) {
        String content = JsonUtil.toJson(info);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

    public LogInOnEvent(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int length = buf.readableBytes();
        byte[] content = new byte[length];
        buf.readBytes(content);
        info = JsonUtil.fromJson(UserInfo.class, content);
    }


}
