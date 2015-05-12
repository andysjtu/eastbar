package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public enum ClientMsgType {
    CLIENT_INIT_REQ((short) 0x1001),
    CLIENT_INIT_RESP((short)0x3001),
    BEATEN((short) 0x0002),
    GEN_RESP((short) 0x0001),
    CLIENT_LOGOUT((short) 0x3002),
    LOCK_CLIENT((short) 0x3003),
    UNLOCK_CLIENT((short) 0x3004),
    SHUTDOWN_CLIENT((short) 0x3005),
    RESTART_CLIENT((short) 0x3006),
    KILL_CLIENT_PORCESS((short) 0x3111),
    QUERY_CLIENT_MODULE((short)0x3101),
    CLOSE_CLIENT_MODULE((short)0x3112),
    QUERY_CLIENT_PROCESS((short)0x3102),
    CAPTURE_CLIENT((short) 0x3007),
    CLIENT_LOG((short) 0x2002),
    CLIENT_ALERT((short) 0x2001),
    UNKNOWN((short) 0xffff);
    private final short typeValue;

    ClientMsgType(short typeValue) {
        this.typeValue = typeValue;
    }


    public static ClientMsgType valueOf(short value) {
        for (ClientMsgType type : ClientMsgType.values()) {
            if (type.shortValue() == value) {
                return type;
            }
        }
        return UNKNOWN;
    }


    public short shortValue() {
        return this.typeValue;
    }
}
