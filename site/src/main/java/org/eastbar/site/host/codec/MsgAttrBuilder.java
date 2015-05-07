package org.eastbar.site.host.codec;

/**
 * Created by andysjtu on 2015/5/7.
 */
public class MsgAttrBuilder {
    private byte typeValue;
    private byte zipValue = (byte) 0;
    private byte crptValue = (byte) 0;


    public MsgAttr buildMsgAttr() {
        byte value = (byte) (typeValue << 4 | crptValue << 2 | zipValue);
        return new MsgAttr(value);
    }

    public MsgAttrBuilder type(MsgAttr.Type type) {
        this.typeValue = type.byteValue();
        return this;
    }

    public MsgAttrBuilder zip(boolean value) {
        if (value) {
            this.zipValue = 2;
        }
        return this;
    }

    public MsgAttrBuilder crpt(boolean value) {
        if (value) {
            this.crptValue = 2;
        }
        return this;
    }

    public static void main(String[] args) {
        MsgAttrBuilder builder = new MsgAttrBuilder();
        MsgAttr attr = builder.type(MsgAttr.Type.SITE_TO_CLIENT).buildMsgAttr();
        System.out.println(Integer.toBinaryString(attr.byteValue()));
    }


}
