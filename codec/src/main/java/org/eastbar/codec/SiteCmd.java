package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * Created by andysjtu on 2015/5/12.
 */
public abstract class SiteCmd extends SocketMsg {
    public SiteCmd(String terminalIP) {
        setMsgAttr(MsgAttrBuilder.buildDefaultCenterToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        try {
            setHost(IpV4.convertIPV4(terminalIP));
        } catch (IPFormatException e) {
            e.printStackTrace();
        }
    }

    public static class LockClientCmd extends SiteCmd {
        public LockClientCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.LOCK_CLIENT.shortValue());
        }
    }

    public static class UnlockClientCmd extends SiteCmd {
        public UnlockClientCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.UNLOCK_CLIENT.shortValue());
        }
    }

    public static class ShutdownClientCmd extends SiteCmd {
        public ShutdownClientCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.SHUTDOWN_CLIENT.shortValue());
        }
    }

    public static class RestartClientCmd extends SiteCmd {
        public RestartClientCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.RESTART_CLIENT.shortValue());
        }
    }

    public static class QueryClientModuleCmd extends SiteCmd {
        public QueryClientModuleCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.QUERY_CLIENT_MODULE.shortValue());
        }
    }

    public static class QueryClientProcessCmd extends SiteCmd {
        public QueryClientProcessCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.QUERY_CLIENT_PROCESS.shortValue());
        }
    }

    public static class CaptureClientCmd extends SiteCmd {
        public CaptureClientCmd(String tip) {
            super(tip);
            setMessageType(ClientMsgType.CAPTURE_CLIENT.shortValue());
        }
    }

    public static class ListTerminalCmd extends SocketMsg {
        public ListTerminalCmd() {
            setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
            setVersion(ProtocolVersion.Version);
            setMessageId(IDGenerator.nextID());
            setHost(IpV4.localIpV4());
            setMessageType(SiteMsgType.LIST.shortValue());
        }
    }

    public static class StatusTerminalCmd extends SocketMsg {
        public StatusTerminalCmd() {
            setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
            setVersion(ProtocolVersion.Version);
            setMessageId(IDGenerator.nextID());
            setHost(IpV4.localIpV4());
            setMessageType(SiteMsgType.STATUS.shortValue());
        }
    }

    public static class KillClientProcess extends SocketMsg {
        public KillClientProcess(String ip, String processId) {
            setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
            setVersion(ProtocolVersion.Version);
            setMessageId(IDGenerator.nextID());
            try {
                setHost(IpV4.convertIPV4(ip));
            } catch (IPFormatException e) {
                e.printStackTrace();
            }
            setMessageType(ClientMsgType.KILL_CLIENT_PORCESS.shortValue());
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes((processId + "\r\n").getBytes(Charsets.UTF_8));
            data(buf);
        }
    }

    public static class KillClientModule extends SocketMsg {
        public KillClientModule(String ip, String moduleId) {
            setMsgAttr(MsgAttrBuilder.buildDefaultCenterToSiteAttr().byteValue());
            setVersion(ProtocolVersion.Version);
            setMessageId(IDGenerator.nextID());
            try {
                setHost(IpV4.convertIPV4(ip));
            } catch (IPFormatException e) {
                e.printStackTrace();
            }
            setMessageType(ClientMsgType.CLOSE_CLIENT_MODULE.shortValue());
            ByteBuf buf = Unpooled.buffer();
            buf.writeBytes((moduleId+"\r\n").getBytes(Charsets.UTF_8));
            data(buf);
        }
    }
}
