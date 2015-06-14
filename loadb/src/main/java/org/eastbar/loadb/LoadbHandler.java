package org.eastbar.loadb;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.loadb.AddressReq;
import org.eastbar.codec.loadb.AddressResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class LoadbHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private final LoadbManager loadbManager;

    public final static Logger logger = LoggerFactory.getLogger(LoadbHandler.class);


    public LoadbHandler(LoadbManager loadbManager) {
        this.loadbManager = loadbManager;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.ADDRESS_REQ.shortValue()) {
            AddressReq req = new AddressReq(msg);
            String reqType = req.getType();
            String siteCode =req.getSiteCode();
            if ("log".equals(reqType)) {
                DomainAndPort domainAndPort = loadbManager.getLogServerAddr(siteCode);
                AddressResp resp = new AddressResp(req.getMessageId(),req.getMessageType(),domainAndPort.getDomain(),domainAndPort.getPort());
                ctx.channel().writeAndFlush(resp);
            } else if ("alert".equals(reqType)) {
                DomainAndPort domainAndPort = loadbManager.getAlertServerAddr(siteCode);
                AddressResp resp = new AddressResp(req.getMessageId(),req.getMessageType(),domainAndPort.getDomain(),domainAndPort.getPort());
                ctx.channel().writeAndFlush(resp);
                System.out.println("--------------------------");
            } else if ("capture".equals(reqType)) {
                DomainAndPort domainAndPort = loadbManager.getCaptureServerAddr(siteCode);
                AddressResp resp = new AddressResp(req.getMessageId(),req.getMessageType(),domainAndPort.getDomain(),domainAndPort.getPort());
                ctx.channel().writeAndFlush(resp);
            } else if ("status".equals(reqType)) {
                DomainAndPort domainAndPort = loadbManager.getStatusServerAddr(siteCode);
                AddressResp resp = new AddressResp(req.getMessageId(),req.getMessageType(),domainAndPort.getDomain(),domainAndPort.getPort());
                ctx.channel().writeAndFlush(resp);
            } else {
                logger.warn("收到未知类型的协议");
                ctx.close();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("error is  :",cause);
        ctx.close();
    }
}
