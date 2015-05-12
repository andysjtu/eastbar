package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andysjtu on 2015/5/9.
 */
public class ClientInitResp extends SocketMsg {

//   private ClientAuthResp authResp;


    public ClientInitResp(short recMessageId,ClientAuthResp resp) {
//        this.authResp = resp;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setHost(IpV4.localIpV4());
        setMessageType(ClientMsgType.CLIENT_INIT_RESP.shortValue());

        data(buildContent(recMessageId,resp));
    }

    private ByteBuf buildContent(short recMsgId,ClientAuthResp resp) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(recMsgId);
        buf.writeShort(ClientMsgType.CLIENT_INIT_REQ.shortValue());
        byte[] content  = resp.toString().getBytes(Charsets.UTF_8);
        buf.writeBytes(content);
        return buf;
    }
}
