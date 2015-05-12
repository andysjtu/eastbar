package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/11.
 */
public abstract class ClientCmd extends SocketMsg {
    public ClientCmd() {
        setMsgAttr(MsgAttrBuilder.buildDefaultSiteToClientAttr().byteValue());
        setVersion(ProtocolVersion.Version);
        setMessageId(IDGenerator.nextID());
        setHost(IpV4.localIpV4());
    }


    public static class LockClientCmd extends ClientCmd {
        public LockClientCmd() {
            setMessageType(ClientMsgType.LOCK_CLIENT.shortValue());
        }
    }

    public  static class UnlockClientCmd extends  ClientCmd{
        public UnlockClientCmd() {
            setMessageType(ClientMsgType.UNLOCK_CLIENT.shortValue());
        }
    }

    public static class ShutdownClientCmd extends  ClientCmd{
        public ShutdownClientCmd() {
            setMessageType(ClientMsgType.SHUTDOWN_CLIENT.shortValue());
        }
    }

    public static class RestartClientCmd extends  ClientCmd{
        public RestartClientCmd() {
            setMessageType(ClientMsgType.RESTART_CLIENT.shortValue());
        }
    }

    public static class CaptureClientCmd extends ClientCmd{
        public CaptureClientCmd() {
            setMessageType(ClientMsgType.CAPTURE_CLIENT.shortValue());
        }
    }

    public static class QueryClientProcessCmd extends  ClientCmd{
        public QueryClientProcessCmd() {
            setMessageType(ClientMsgType.QUERY_CLIENT_PROCESS.shortValue());
        }
    }

    public static class QueryClientModuleCmd extends  ClientCmd{
        public QueryClientModuleCmd() {
            setMessageType(ClientMsgType.QUERY_CLIENT_MODULE.shortValue());
        }
    }
}
