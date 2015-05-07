package org.eastbar.site.host.codec;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public enum ProtocolVersion {
    Version((byte) 1), UNKNOWN((byte) 0xff);
    private final byte value;

    ProtocolVersion(byte value) {
        this.value = value;
    }

    public static ProtocolVersion valueOf(byte value) {
        for (ProtocolVersion version : ProtocolVersion.values()) {
            if (version.value == value) {
                return version;
            }
        }
        return UNKNOWN;
    }

    public byte byteValue(){
        return value;
    }
}
