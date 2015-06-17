package org.eastbar.codec.biz;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public class CustomLoginMsg extends SocketMsg {
    private UserInfo userInfo = null;

    public CustomLoginMsg(UserInfo userInfoList) {
        this.userInfo = userInfoList;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.CUSTOMER_LOGIN.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(userInfoList));
    }

    public CustomLoginMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.userInfo = JsonUtil.fromJson(UserInfo.class, content);
    }

    protected ByteBuf toByteBuf(UserInfo report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }
}
