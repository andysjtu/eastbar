package org.eastbar.codec;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by AndySJTU on 2015/5/6.
 */
public class ClientInitReq   {
    private ClientAuthScheme authSchme;
    
    public static final  Logger logger = LoggerFactory.getLogger(ClientInitReq.class);

    private final SocketMsg msg;

    public SocketMsg getMsg() {
        return msg;
    }

    public ClientInitReq(SocketMsg msg) {
        this.msg = msg;
    }

    public void parse(){
        try{
            parseContent(msg.data().content());
        }finally {
            msg.release();
        }
    }


    protected void parseContent(ByteBuf buf) {
        String content = buf.toString(Charsets.UTF_8);
        logger.info("client init info is : {}", content);
        Map<String,String> map = Splitter.on("\r\n").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(content);
        authSchme = new ClientAuthScheme();
        authSchme.setIpAddress(map.get("IPAddress"));
        authSchme.setMacAddress(map.get("MacAddress"));
        authSchme.setOs(map.get("Os"));
        authSchme.setVersion(map.get("Version"));
    }

    public ClientAuthScheme getAuthSchme() {
        return authSchme;
    }

    public short getMessageId() {
        return msg.getMessageId();
    }
}
