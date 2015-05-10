package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public enum ClientMsgType {
    CLIENT_INIT_REQ((byte) 0x1001),
    CLIENT_INIT_RESP((byte)0x3001),
    BEATEN((byte) 0x0002),
    GEN_RESP((byte) 0x0001),
    CLIENT_LOGOUT((byte) 0x3002),
    LOCK_CLIENT((byte) 0x3003),
    UNLOCK_CLIENT((byte) 0x3004),
    SHUTDOWN_CLIENT((byte) 0x3005),
    RESTART_CLIENT((byte) 0x3006),
    KILL_CLIENT_PORCESS((byte) 0x3111),
    CAPTURE_CLIENT((byte) 0x3007),
    CLIENT_LOG((byte) 0x2002),
    CLIENT_ALERT((byte) 0x2001),
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
