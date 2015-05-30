package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public class BeatenEvent extends SocketMsg {
    public BeatenEvent() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        short value = IDGenerator.nextID();
        setMessageId(value);
        setMessageType(SiteMsgType.BEATEN.shortValue());
        setHost(IpV4.localIpV4());
    }

    public BeatenEvent(SocketMsg oldMsg) {
        super(oldMsg);
    }
}
