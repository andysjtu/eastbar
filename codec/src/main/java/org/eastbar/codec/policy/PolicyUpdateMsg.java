package org.eastbar.codec.policy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;
import org.eastbar.comm.log.entity.EmailLog;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public class PolicyUpdateMsg extends SocketMsg {
    private byte[] content;

    public PolicyUpdateMsg(POLICY_TYPE type, byte[] content) {
        this.content = content;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        switch (type) {
            case URL:
                setMessageType(SiteMsgType.UPDATE_URL_POLICY.shortValue());
                break;
            case PROGRAM:
                setMessageType(SiteMsgType.UPDATE_PROG_POLICY.shortValue());
                break;
            case KEYWORD:
                setMessageType(SiteMsgType.UPDATE_KW_POLICY.shortValue());
                break;
            case SPEICAL_PERSON:
                setMessageType(SiteMsgType.UPDATE_SP_POLICY.shortValue());
                break;
        }

        setHost(IpV4.localIpV4());
        data(toByteBuf(content));
    }

    public PolicyUpdateMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        int contentLength = buf.readableBytes();
        content = new byte[contentLength];
        buf.readBytes(content);
    }

    protected ByteBuf toByteBuf(byte[] content) {
        return Unpooled.wrappedBuffer(content);
    }

    public byte[] getContent() {
        return content;
    }


    public static enum POLICY_TYPE {
        URL, PROGRAM, KEYWORD, SPEICAL_PERSON
    }

}
