package org.eastbar.codec;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.base.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by AndySJTU on 2015/5/13.
 */
public class JsonUtil {
    public static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        SimpleModule module = new SimpleModule("EnhancedDatesModule", new Version(0, 1, 0, "alpha"));

        module.addKeyDeserializer(SiteReport.class,new SiteReportDeserializer());

        objectMapper.registerModule(module);
    }
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

    public static <T> T fromJson(TypeReference<T> tr,byte[] content){
        T t=null;
        try {
            t = objectMapper.readValue(content,tr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
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
        scheme.setIp("192.168.9.245");
        scheme.setOs("系统");

        System.out.println(toJson(scheme));

        System.out.println(fromJson(ClientAuthScheme.class,toJson(scheme).getBytes(Charsets.UTF_8)));
    }


    private static class SiteReportDeserializer extends KeyDeserializer {
        @Override
        public Object deserializeKey(String s, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return JsonUtil.fromJson(SiteReport.class,s.getBytes(Charsets.UTF_8));
        }
    }


}
