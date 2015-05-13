package org.eastbar.codec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public class JsonUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();
    public final static Logger logger= LoggerFactory.getLogger(JsonUtil.class);

    public static <T> String toJson(T obj){
        StringWriter writer = new StringWriter();
        try {
            objectMapper.writeValue(writer,obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    public static <T> T fromJson(Class<T> clz,byte[] content){
        T t=null;
        try {
            t = objectMapper.readValue(content,clz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] args) {
        ClientAuthScheme scheme = new ClientAuthScheme();
        scheme.setIpAddress("192.168.9.245");
        scheme.setOs("系统");

        System.out.println(toJson(scheme));

        System.out.println(fromJson(ClientAuthScheme.class,toJson(scheme).getBytes(Charsets.UTF_8)));
    }
}
