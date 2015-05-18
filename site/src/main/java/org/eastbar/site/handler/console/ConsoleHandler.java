package org.eastbar.site.handler.console;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import org.eastbar.codec.*;
import org.eastbar.site.handler.client.ClientProxyChannelHandler;
import org.eastbar.site.Site;
import org.eastbar.site.SiteServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static Logger logger = LoggerFactory.getLogger(ConsoleHandler.class);


    private final ClientProxyChannelHandler proxyChannelHandler;


    private final SiteServer siteServer;

    public ConsoleHandler(ClientProxyChannelHandler proxyChannelHandler, SiteServer siteServer) {
        this.proxyChannelHandler = proxyChannelHandler;
        this.siteServer = siteServer;
    }


//    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        System.out.println("receive String is : " + msg);
//        if ("squit".equalsIgnoreCase(msg)) {
//            logger.info("收到控制端指令退出");
//            siteServer.stop();
//        } else if ("list".equalsIgnoreCase(msg)) {
//            Site site = siteServer.getSite();
//            Set<String> hosts = site.getHosts();
//            ctx.channel().writeAndFlush("hosts " + hosts + "\r\n");
//        } else if (msg.startsWith("lock ")) {
//            lockClient(msg, ctx.channel());
//
//        } else if (msg.startsWith("unlock ")) {
//            unlockClient(msg, ctx.channel());
//        } else if (msg.startsWith("restart ")) {
//            restartClient(msg);
//        } else if (msg.startsWith("shutdown ")) {
//            shutdownClient(msg);
//        } else if (msg.startsWith("capture ")) {
//            captureClient(msg);
//        } else if (msg.startsWith("queryp ")) {
//            queryClientProcess(msg, ctx.channel());
//        }else if (msg.startsWith("querym ")) {
//            queryClientModule(msg, ctx.channel());
//        }else if(msg.startsWith("querypm ")){
//            String hostiP = trimHostIp(msg,"querypm");
//
//            Channel channel = findTerminalChannel(hostiP);
//            System.out.println("channel is : "+channel);
//            if(channel!=null){
//                sendQueryClientProcessCmd(ctx.channel(), channel);
//                sendQueryClientModuleCmd(ctx.channel(),channel);
//            }
//        }
//    }
//
//    private String trimHostIp(String msg,String key){
//        String hostIp = msg.replace(key, "").trim();
//        return hostIp;
//    }

//    public void sendCmd(String hostIp,Channel consoleChannel){
//        Channel channel = findTerminalChannel(hostIp);
//        if(channel!=null){
//
//        }
//    }

    private Channel findTerminalChannel(String host) {
        Site site = siteServer.getSite();
        Channel channel = site.getTerminalChannel(host);
        return channel;
    }



    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final SocketMsg msg) throws Exception {
        MsgAttr attr = new MsgAttr(msg.getMsgAttr());

        if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
            Set<String> hosts = siteServer.getSite().getHosts();
            SocketMsg newMsg = new SocketMsg(msg);
            newMsg.setMsgAttr(MsgAttrBuilder.buildDefaultSiteToCenterAttr().byteValue());
            ByteBuf buf = Unpooled.buffer();
            if(hosts.size()==0){
                buf.writeBytes("没有终端连上\n".getBytes(Charsets.UTF_8));
            }else {
                for (String host : hosts) {
                    buf.writeBytes(host.getBytes(Charsets.UTF_8));
                    buf.writeBytes("\n".getBytes());
                }
            }
            newMsg.data(buf);
            ctx.writeAndFlush(newMsg);
        } else {
            String ip = msg.getHost().toRegularIpFormat();
            Channel channel = findTerminalChannel(ip);
            if (channel != null) {
                msg.changeSiteToClientAttr();
                channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if(future.isSuccess()){
                            proxyChannelHandler.registerTarget(msg.getMessageId(), msg.getMessageType(), ctx.channel());
                        }
                    }
                });
            }
        }
    }



}
