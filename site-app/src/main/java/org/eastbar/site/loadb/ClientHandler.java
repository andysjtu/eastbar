package org.eastbar.site.loadb;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Promise;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.SiteMsgType;
import org.eastbar.codec.SocketMsg;
import org.eastbar.codec.loadb.AddressResp;
import org.eastbar.net.DomainAndPort;

/**
 * Created by AndySJTU on 2015/6/18.
 */
public class ClientHandler extends SimpleChannelInboundHandler<SocketMsg> {

    private final short messageType;
    private final short messageId;
    private final SocketMsg msg;

    private volatile DomainAndPort domainAndPort;
    private volatile boolean isSend = false;
    private volatile Promise promise;

    public ClientHandler(SocketMsg msg,EventExecutor executor) {
        this.msg = msg;
        this.messageType = msg.getMessageType();
        this.messageId = msg.getMessageId();
        promise = new DefaultPromise<DomainAndPort>(executor);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        if (ctx.channel().isActive() && ctx.channel().isRegistered()) {
            sendMsg(ctx);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        sendMsg(ctx);
    }

    private void sendMsg(ChannelHandlerContext ctx) {
        if (isSend) return;
        else {
            ctx.channel().writeAndFlush(msg);
            isSend = true;
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.GEN_RESP.shortValue()) {
            GenResp resp = new GenResp(msg);
            short recMsgId = resp.getRecMessageId();
            short recMsgType = resp.getRecMessageType();
            if (recMsgId == messageId && messageType == recMsgType) {
                promise.setFailure(new Exception("not get the result"));
                ctx.pipeline().remove(this);
                return;
            }
        } else if (type == SiteMsgType.ADDRESS_RESP.shortValue()) {
            AddressResp resp = new AddressResp(msg);
            short recMsgId = resp.getRecMessageId();
            short recMsgType = resp.getRecMessageType();
            if (recMsgId == messageId && messageType == recMsgType) {
                domainAndPort = new DomainAndPort(resp.getDomain(), resp.getPort());
                promise.setSuccess(domainAndPort);
                ctx.pipeline().remove(this);
                return;
            }

        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }

    public Promise getPromise() {
        return promise;
    }

}
