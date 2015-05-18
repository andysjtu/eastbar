package org.eastbar.codec;

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
}
