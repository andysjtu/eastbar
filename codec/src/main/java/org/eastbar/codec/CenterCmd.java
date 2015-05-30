package org.eastbar.codec;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by AndySJTU on 2015/5/25.
 */
public abstract class CenterCmd extends SocketMsg {
    public CenterCmd(String siteCode, String hostIp) {
        setMsgAttr(MsgAttrBuilder.buildDefaultCenterToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        try {
            setHost(IpV4.convertIPV4(hostIp));
        } catch (IPFormatException e) {
            e.printStackTrace();
        }
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(siteCode.getBytes());
        data(buf);
    }

    public CenterCmd(String siteCode) {
        setMsgAttr(MsgAttrBuilder.buildDefaultCenterToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setHost(IpV4.localIpV4());
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(siteCode.getBytes());
        data(buf);
    }

    public static class ListClientCmd extends CenterCmd {
        public ListClientCmd(String siteCode) {
            super(siteCode);
            setMessageType(SiteMsgType.LIST.shortValue());
        }
    }

    public static class LockClientCmd extends CenterCmd {
        public LockClientCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.LOCK_CLIENT.shortValue());
        }
    }

    public static class UnlockClientCmd extends CenterCmd {
        public UnlockClientCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.UNLOCK_CLIENT.shortValue());
        }
    }

    public static class ShutdownClientCmd extends CenterCmd {
        public ShutdownClientCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.SHUTDOWN_CLIENT.shortValue());
        }
    }

    public static class RestartClientCmd extends CenterCmd {
        public RestartClientCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.RESTART_CLIENT.shortValue());
        }
    }

    public static class QueryClientModuleCmd extends CenterCmd {
        public QueryClientModuleCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.QUERY_CLIENT_MODULE.shortValue());
        }
    }

    public static class QueryClientProcessCmd extends CenterCmd {
        public QueryClientProcessCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.QUERY_CLIENT_PROCESS.shortValue());
        }
    }

    public static class CaptureClientCmd extends CenterCmd {
        public CaptureClientCmd(String siteCode, String hostIp) {
            super(siteCode, hostIp);
            setMessageType(ClientMsgType.CAPTURE_CLIENT.shortValue());
        }
    }

    public static class StatusCmd extends SocketMsg {
        public StatusCmd() {
            setMsgAttr(MsgAttrBuilder.buildDefaultCenterToClientAttr().byteValue());
            setVersion(ProtocolVersion.Version);
            setMessageId(IDGenerator.nextID());
            setHost(IpV4.localIpV4());
            setMessageType(SiteMsgType.STATUS.shortValue());
        }


    }
}
