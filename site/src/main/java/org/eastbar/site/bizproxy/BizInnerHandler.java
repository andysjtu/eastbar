package org.eastbar.site.bizproxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.UserInfo;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizInnerHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final BizProxyServer bizProxyServer;

    public BizInnerHandler(BizProxyServer proxyServer) {
        this.bizProxyServer = proxyServer;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        List<UserInfo> allHosts = bizProxyServer.getAllUserInfos();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {

    }
}
