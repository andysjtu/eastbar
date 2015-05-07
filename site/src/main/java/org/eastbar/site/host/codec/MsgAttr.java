package org.eastbar.site.host.codec;

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
}
