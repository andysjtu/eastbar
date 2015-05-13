package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public class SiteBeatenEvent extends SocketMsg {
    public SiteBeatenEvent() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.BEATEN.shortValue());
        setHost(IpV4.localIpV4());
    }

    public SiteBeatenEvent(SocketMsg oldMsg) {
        super(oldMsg);
    }
}
