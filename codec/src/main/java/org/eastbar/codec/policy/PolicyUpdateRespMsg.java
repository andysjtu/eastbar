package org.eastbar.codec.policy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class PolicyUpdateRespMsg extends SocketMsg {
    private short policyType;
    private short status;

    public PolicyUpdateRespMsg(short policyType, short status) {
        this.policyType = policyType;
        this.status = status;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.UPDATE_POLICY_RESP.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(policyType, status));
    }

    protected ByteBuf toByteBuf(short policyType, short status) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(policyType);
        buf.writeShort(status);
        return buf;
    }


    public PolicyUpdateRespMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        policyType = buf.readShort();
        status = buf.readShort();
    }
}
