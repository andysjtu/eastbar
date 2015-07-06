package org.eastbar.codec.server;

import org.eastbar.codec.*;

/**
 * Created by andysjtu on 2015/7/3.
 */
public class PingMsg extends SocketMsg {
    public PingMsg() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.PING_REQ.shortValue());
        setHost(IpV4.localIpV4());
    }
}
