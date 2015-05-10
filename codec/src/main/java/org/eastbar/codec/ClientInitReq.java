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
public class ClientInitReq extends SocketMsg {
    private ClientAuthScheme authSchme;
    
    public static final  Logger logger = LoggerFactory.getLogger(ClientInitReq.class);


    @Override
    protected void parseContent(ByteBuf buf) {
        String content = buf.toString(Charsets.UTF_8);
        logger.debug("client init info is : {}",content);
        Map<String,String> map = Splitter.on("\r\n").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(content);
        authSchme = new ClientAuthScheme();
        authSchme.setIpAddress(map.get("ipAddress"));
        authSchme.setMacAddress(map.get("macAddress"));
        authSchme.setOs(map.get("os"));
        authSchme.setVersion(map.get("version"));
    }

    public ClientAuthScheme getAuthSchme() {
        return authSchme;
    }
}
