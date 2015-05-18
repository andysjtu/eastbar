package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public enum SiteMsgType {
    GEN_RESP((short) 0x0001),
    BEATEN((short) 0x0002),
    INIT_CONN((short) 0x4000),
    LOGIN((short) 0x4001),
    ONLINE((short) 0x4002),
    OFFLINE((short) 0x4003),
    LOGOUT((short) 0x4004),
    LIST((short) 0x6001),
    UNKNOWN((short) 0xffff);


    private final short typeValue;

    SiteMsgType(short typeValue) {
        this.typeValue = typeValue;
    }


    public static SiteMsgType valueOf(short value) {
        for (SiteMsgType type : SiteMsgType.values()) {
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
