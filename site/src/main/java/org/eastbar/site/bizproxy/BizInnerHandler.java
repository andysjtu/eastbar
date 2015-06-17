package org.eastbar.site.bizproxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.UserInfo;
import org.eastbar.codec.biz.UserInfoMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizInnerHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger = LoggerFactory.getLogger(BizInnerHandler.class);

    private final BizProxyServer bizProxyServer;

    public BizInnerHandler(BizProxyServer proxyServer) {
        this.bizProxyServer = proxyServer;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
         short type = msg.getMessageType();
         if(type== SiteMsgType.GEN_RESP.shortValue()){
             //do nothing
         }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("some error happened: {}", cause.getMessage());
        ctx.close();
    }
}
