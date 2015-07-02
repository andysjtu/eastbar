package org.eastbar.web;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午2:52
 * @description : IP与数值间相互转换
 */
public class Ip2Number {


    /**
     * @return 有符号的整形数；当ip>128.0.0.0时为负数；
     */
    public static int ip2Int(String ip) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(ip);// 在给定主机名的情况下确定主机的 IP 址。
        byte[] bytes = address.getAddress();// 返回此 InetAddress 对象的原始 IP 地址
        int a, b, c, d;
        a = byte2int(bytes[0]);
        b = byte2int(bytes[1]);
        c = byte2int(bytes[2]);
        d = byte2int(bytes[3]);
        int result = (a << 24) | (b << 16) | (c << 8) | d;
        return result;
    }

    public static int byte2int(byte b) {
        int l = b & 0x07f;
        if (b < 0) {
            l |= 0x80;
        }
        return l;
    }

    /**
     * @return IP转化为长整形数值
     */
    public static long ip2long(String ip) throws UnknownHostException {
        int ipNum = ip2Int(ip);
        return int2long(ipNum);
    }

    public static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }

    /**
     * @return 长整形数值转化为ip地址
     */
    public static String long2ip(long number) {
        int[] b = new int[4];
        b[0] = (int) ((number >> 24) & 0xff);
        b[1] = (int) ((number >> 16) & 0xff);
        b[2] = (int) ((number >> 8) & 0xff);
        b[3] = (int) (number & 0xff);
        String x;
//        Integer p = new Integer(0);
//        System.out.println(b[0]);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2])
                + "." + Integer.toString(b[3]);

        return x;

    }

    // 测试函数
//    public static void main(String[] args) throws Exception {
//        Integer ip = ip2Int("192.168.9.143");
//
//        System.out.println(ip);
//        System.out.println(ip2long("192.168.0.250"));
//        System.out.println(long2ip(ip));
//
//    }

}
