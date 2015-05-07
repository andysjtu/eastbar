package org.eastbar.site.host.codec;

import org.eastbar.sitecmd.IPFormatException;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by AndySJTU on 2015/3/7.
 */
public class IpV4 {
    private byte[] ipBytes;

    public IpV4(byte[] bytes) {
        if (bytes.length == 4) {
            ipBytes = bytes;
        } else {
            throw new RuntimeException("ip format is wrong,please check");
        }
    }

    public String toRegularIpFormat() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int ip = ipBytes[i] > 0 ? ipBytes[i] : ipBytes[i] + 256;
            builder.append(ip);
            if (i < 3)
                builder.append(".");
        }
        return builder.toString();
    }

    public String toEastBarFormat(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int ip = ipBytes[i] > 0 ? ipBytes[i] : ipBytes[i] + 256;
            if(ip<10){
               builder.append("00");
            }else if(ip<100){
                builder.append("0");
            }
            builder.append(ip);
            if (i < 3)
                builder.append(".");
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IPFormatException {
//        byte x = (byte)0xFF;
//        System.out.println("x is : "+x);
//        byte[] ipbs = new byte[]{x,23,34,5};

        byte[] ipbs = localIpBytes();

        IpV4 ip = new IpV4(ipbs);

        System.out.println(ip.toRegularIpFormat());
        System.out.println(ip.toEastBarFormat());

        IpV4 testIP = IpV4.convertIPV4("244.255.001.244");
        System.out.println(testIP.toRegularIpFormat());
        System.out.println(testIP.toEastBarFormat());
    }

    public byte[] getContent(){
        return ipBytes;
    }

    public static IpV4 localIpV4(){
        return new IpV4(localIpBytes());
    }

    public static byte[] localIpBytes() {
        byte[] result = new byte[]{0,0,0,0};
        try {
           result = InetAddress.getLocalHost().getAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String toString() {
        return "IpV4{" +
                "ipBytes=" + Arrays.toString(ipBytes) +
                '}';
    }

    public static IpV4 convertIPV4(String terminalIP) throws IPFormatException {
        String[] ips = terminalIP.split("\\.");
        System.out.println(Arrays.toString(ips));
        if(ips.length!=4){
            throw new IPFormatException("terminalIP format is wrong : "+terminalIP);
        }
        byte[] ipbytes = new byte[4];

        for(int i=0;i<4;i++){
            try {
                ipbytes[i] = (byte) Integer.parseInt(ips[i]);
            }catch(NumberFormatException e){
                throw new IPFormatException("terminalIP format is wrong : "+terminalIP);
            }
        }
        return new IpV4(ipbytes);
    }


}
