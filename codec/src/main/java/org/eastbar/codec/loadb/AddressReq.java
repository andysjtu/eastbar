package org.eastbar.codec.loadb;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class AddressReq extends SocketMsg {
    private String siteCode;
    private String type;

    public AddressReq(String siteCode,String type) {
        this.siteCode = siteCode;
        this.type = type;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.ADDRESS_REQ.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(siteCode, type));
    }

    public AddressReq(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        String all= buf.toString(Charsets.UTF_8);
        String[] contents = all.split("\r\n");
        if(contents.length==2){
            siteCode= contents[0];
            type = contents[1];
        }

    }

    protected ByteBuf toByteBuf(String siteCode,String type) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(siteCode.getBytes(Charsets.UTF_8));
        buf.writeBytes("\r\n".getBytes(Charsets.UTF_8));
        buf.writeBytes(type.getBytes(Charsets.UTF_8));
        return buf;
    }


    public String getType() {
        return type;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
}
