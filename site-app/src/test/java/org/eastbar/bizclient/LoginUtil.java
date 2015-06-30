package org.eastbar.bizclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * Created by andysjtu on 2015/6/30.
 */
public class LoginUtil {
    public static ByteBuf mockLogin(String name, String id, String ip, String time) throws UnsupportedEncodingException {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1101);
        buf.writeBytes("1.0".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("netbar".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1234567".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode(name.getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode(id.getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("徐汇公安局".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(ip.getBytes());
        buf.writeByte('\0');
        buf.writeBytes(time.getBytes());
        buf.writeByte('\0');
        return buf;
    }


    public static ByteBuf mockLogout(String name, String id, String ip, String loginTime, String logoutTime) throws UnsupportedEncodingException {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1102);
        buf.writeBytes("1.0".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("netbar".getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1234567".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode(name.getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes("1".getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode(id.getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(new BASE64Encoder().encode("徐汇公安局".getBytes("GBK")).getBytes());
        buf.writeByte('\0');
        buf.writeBytes(ip.getBytes());
        buf.writeByte('\0');
        buf.writeBytes(loginTime.getBytes());
        buf.writeByte('\0');
        buf.writeBytes(logoutTime.getBytes());
        buf.writeByte('\0');

        return buf;
    }
}
