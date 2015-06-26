package org.eastbar.site;

import io.netty.channel.Channel;
import org.eastbar.codec.ClientMsgType;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public interface Proxy {
    public boolean isOpen();
    public Channel proxyChannel();

    boolean contains(short msgType, short messageId);
}
