package org.eastbar.codec.loadb;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class AddressResp extends SocketMsg {
    private short recMessageId;
    private short recMessageType;
    private int port;
    private String domain;

    public AddressResp(short recMessageId, short recMessageType, String domain, int port) {
        this.port = port;
        this.domain = domain;
        this.recMessageId = recMessageId;
        this.recMessageType = recMessageType;
        setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.ADDRESS_RESP.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(recMessageId, recMessageType, domain, port));
    }

    public AddressResp(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        recMessageId = buf.readShort();
        recMessageType = buf.readShort();
        port = buf.readInt();
        domain = buf.toString(Charsets.UTF_8);

    }

    protected ByteBuf toByteBuf(short recMessageId, short recMessageType, String domain, int port) {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShort(recMessageId);
        buf.writeShort(recMessageType);
        buf.writeInt(port);
        buf.writeBytes(domain.getBytes(Charsets.UTF_8));
        return buf;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public short getRecMessageId() {
        return recMessageId;
    }

    public void setRecMessageId(short recMessageId) {
        this.recMessageId = recMessageId;
    }

    public short getRecMessageType() {
        return recMessageType;
    }

    public void setRecMessageType(short recMessageType) {
        this.recMessageType = recMessageType;
    }


}
