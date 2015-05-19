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

    public static final Logger logger = LoggerFactory.getLogger(ClientInitReq.class);


    public ClientInitReq(SocketMsg msg) {
        super(msg);
        parseContent(msg.data().content());
    }


    protected void parseContent(ByteBuf buf) {
        String content = buf.toString(Charsets.UTF_8);
        Map<String, String> map = Splitter.on("\r\n").trimResults().omitEmptyStrings().withKeyValueSeparator(":").split(content);
        authSchme = new ClientAuthScheme();
        authSchme.setIpAddress(map.get("IPAddress"));
        authSchme.setMacAddress(map.get("MacAddress"));
        authSchme.setOs(map.get("Os"));
        authSchme.setVersion(map.get("Version"));
    }

    public ClientAuthScheme getAuthSchme() {
        return authSchme;
    }

}
