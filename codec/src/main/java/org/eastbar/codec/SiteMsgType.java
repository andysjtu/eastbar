package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public enum SiteMsgType {
    GEN_RESP((short) 0x0001),
    BEATEN((short) 0x0002),
    SITE_INIT_CONN((short) 0x4000),
    SITE_DISC_CONN((short)0x4010),
    LOGIN((short) 0x4001),
    ONLINE((short) 0x4002),
    OFFLINE((short) 0x4003),
    LOGOUT((short) 0x4004),
    LIST((short) 0x6001),
    STATUS((short) 0x6002),

    UPDATE_URL_POLICY((short) 0x6003),
    UPDATE_PROG_POLICY((short) 0x6007),
    UPDATE_KW_POLICY((short) 0x6008),
    UPDATE_SP_POLICY((short) 0x6009),

    UPDATE_POLICY_RESP((short) 0x6010),

    USER_INFO_MSG((short)0x6020),
    CONSOLE_CMD_MSG((short)0x6021),

    TERM_STATUS((short) 0x6004),
    POLICY_STATUS((short) 0x6005),
    CENTER_INIT_CONN((short) 0x6006),
    //////////////////////////////////////
    ILLEGAL_LOG((short) 0x7001),
    EMAIL_LOG((short) 0x7002),
    INST_MSG_LOG((short) 0x7003),
    PROG_MSG_LOG((short) 0x7004),

    URL_LOG((short) 0x7005),
    ///////////////////////
    URL_ALERT((short) 0x8001),
    PROG_ALERT((short) 0x8002),
    ILLEGAL_ALERT((short) 0x8003),
    GENERAL_ALERT((short) 0x8004),


    ADDRESS_REQ((short)0x9001),
    ADDRESS_RESP((short)0x9002),

    CUSTOMER_LOGIN((short)0x9010),
    CUSTOMER_LOGOUT((short)0x9011),
    CUSTOMER_ALL((short)0x9012),

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
