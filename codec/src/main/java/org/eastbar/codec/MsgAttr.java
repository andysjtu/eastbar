package org.eastbar.codec;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class MsgAttr {
    private final byte attrValue;

    public MsgAttr(byte attrValue) {
        this.attrValue = attrValue;
    }

    public byte byteValue(){
        return attrValue;
    }

    public Type msgType(){
        return Type.valueOf((byte)(attrValue>>4));
    }

    public static enum Type {
        CLIENT_TO_SITE((byte) 1), SITE_TO_CLIENT((byte) 2), SITE_TO_CENTER((byte) 3), CENTER_TO_SITE((byte) 4), CENTER_TO_CLIENT((byte) 5), UNKNOWN((byte) 0xff);

        Type(byte value) {
            this.value = value;
        }

        private final byte value;

        public static Type valueOf(byte value) {
            for (Type type : values()) {
                if (type.value == value) {
                    return type;
                }
            }
            return UNKNOWN;
        }

        public byte byteValue() {
            return this.value;
        }
    }
}
