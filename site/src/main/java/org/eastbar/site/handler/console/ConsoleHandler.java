package org.eastbar.site.handler.console;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.site.SiteServer;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<String> {

    private final SiteServer siteServer;

    public ConsoleHandler(SiteServer siteServer) {
        this.siteServer = siteServer;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("receive String is : "+msg);
        if("quit".equalsIgnoreCase(msg)){
            //TODO
            siteServer.stop();
        }
    }
}
