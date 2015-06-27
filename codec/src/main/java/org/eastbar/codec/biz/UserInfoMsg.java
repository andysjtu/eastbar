package org.eastbar.codec.biz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class UserInfoMsg extends SocketMsg {
    private List<UserInfo> userInfoList = Lists.newArrayList();

    public UserInfoMsg(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.USER_INFO_MSG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(userInfoList));
    }

    public UserInfoMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    public List<UserInfo> getUserInfos() {
        return userInfoList;
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        byte[] content = new byte[contentLength];
        buf.readBytes(content);
        this.userInfoList = JsonUtil.fromJson(new TypeReference<List<UserInfo>>() {
        }, content);
    }

    protected ByteBuf toByteBuf(List<UserInfo> report) {
        String content = JsonUtil.toJson(report);
        return Unpooled.wrappedBuffer(content.getBytes(Charsets.UTF_8));
    }

}
