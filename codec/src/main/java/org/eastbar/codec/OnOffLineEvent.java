package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public class OnOffLineEvent extends SocketMsg{

    private ClientAuthScheme scheme;

    public OnOffLineEvent(SocketMsg socketMsg){
        super(socketMsg);
        ByteBuf buf = socketMsg.data().content();
        byte[] content = new byte[buf.readableBytes()];
        buf.readBytes(content);
        scheme = JsonUtil.fromJson(ClientAuthScheme.class,content);
    }


    public OnOffLineEvent(ClientAuthScheme scheme,boolean online) {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        if(online) {
            setMessageType(SiteMsgType.ONLINE.shortValue());
        }else{
            setMessageType(SiteMsgType.OFFLINE.shortValue());
        }
        setHost(IpV4.localIpV4());
        data(convertContent(scheme));
        this.scheme = scheme;
    }

    private ByteBuf convertContent(ClientAuthScheme scheme){
        ByteBuf buf = Unpooled.wrappedBuffer(JsonUtil.toJson(scheme).getBytes(Charsets.UTF_8));
        return buf;
    }

    public ClientAuthScheme getScheme() {
        return scheme;
    }

    public void setScheme(ClientAuthScheme scheme) {
        this.scheme = scheme;
    }
}
