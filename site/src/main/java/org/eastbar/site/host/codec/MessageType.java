package org.eastbar.site.host.codec;

import java.awt.*;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public enum MessageType {
    UNKNOWN((short) 0xffff);
    private final short typeValue;

    MessageType(short typeValue) {
        this.typeValue = typeValue;
    }


    public static MessageType valueOf(short value) {
        for (MessageType type : MessageType.values()) {
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
