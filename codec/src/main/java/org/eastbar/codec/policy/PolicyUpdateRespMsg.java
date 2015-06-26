package org.eastbar.codec.policy;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class PolicyUpdateRespMsg extends SocketMsg {
    private short policyType;
    private short status;
    private int curVersion;
    private String siteCode;

    public PolicyUpdateRespMsg(short policyType, short status,int curVersion,String siteCode) {
        this.policyType = policyType;
        this.status = status;
        this.curVersion = curVersion;
        this.siteCode = siteCode;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.UPDATE_POLICY_RESP.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(policyType, status,curVersion,siteCode));
    }

    protected ByteBuf toByteBuf(short policyType, short status,int curVersion,String siteCode) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(policyType);
        buf.writeShort(status);
        buf.writeInt(curVersion);
        buf.writeBytes(siteCode.getBytes(Charsets.UTF_8));
        return buf;
    }


    public PolicyUpdateRespMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        policyType = buf.readShort();
        status = buf.readShort();
        curVersion = buf.readInt();
        siteCode = buf.toString(Charsets.UTF_8);
    }
}
