package org.eastbar.site;

import io.netty.channel.Channel;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public class ChannelProxy implements Proxy {

    private volatile Channel proxyChannel;



    public ChannelProxy(Channel channel) {
        proxyChannel = channel;
    }



    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public Channel proxyChannel() {
        return null;
    }

    @Override
    public boolean contains(short msgType, short messageId) {
        return false;
    }
}
