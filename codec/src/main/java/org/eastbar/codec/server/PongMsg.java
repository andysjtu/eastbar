package org.eastbar.codec.server;

import org.eastbar.codec.*;

/**
 * Created by andysjtu on 2015/7/3.
 */
public class PongMsg extends SocketMsg {
    public PongMsg() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.PONG_RESP.shortValue());
        setHost(IpV4.localIpV4());
    }
}
