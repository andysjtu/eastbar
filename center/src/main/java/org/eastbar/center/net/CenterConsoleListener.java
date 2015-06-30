package org.eastbar.center.net;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.eastbar.codec.console.ConsoleListener;
import org.springframework.stereotype.Component;

/**
 * Created by andysjtu on 2015/6/30.
 */
@Component
public class CenterConsoleListener extends ConsoleListener {
    private CenterHub centerHub;

    public CenterConsoleListener() {
        setListenPort(8888);
    }

    @Override
    protected void configHandler(SocketChannel ch) {
        super.configHandler(ch);
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("customHandler",new CenterConsoleHandler(centerHub));
    }
}
