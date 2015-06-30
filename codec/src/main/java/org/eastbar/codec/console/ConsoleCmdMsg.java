package org.eastbar.codec.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.eastbar.codec.*;

/**
 * Created by andysjtu on 2015/6/29.
 */
public class ConsoleCmdMsg extends SocketMsg {
    private String cmdStr;


    public ConsoleCmdMsg(String cmdStr) {
        this.cmdStr = cmdStr;
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setMessageType(SiteMsgType.CONSOLE_CMD_MSG.shortValue());
        setHost(IpV4.localIpV4());
        data(toByteBuf(cmdStr));
    }

    public ConsoleCmdMsg(SocketMsg oldMsg) {
        super(oldMsg);
        parseContent(oldMsg.data().content());
    }

    protected void parseContent(ByteBuf buf) {
        this.cmdStr = buf.toString(Charsets.UTF_8);
    }

    protected ByteBuf toByteBuf(String cmdStr) {
        return Unpooled.wrappedBuffer(cmdStr.getBytes(Charsets.UTF_8));
    }
}
