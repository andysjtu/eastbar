package org.eastbar.center.handler.site;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;
import org.assertj.core.util.Maps;
import org.eastbar.codec.EntryKey;
import org.eastbar.codec.SocketMsg;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/15.
 */
public class SiteCmdRespHandler extends SimpleChannelInboundHandler<SocketMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {

    }

    private Map<EntryKey, Runnable> runnables = Maps.newConcurrentHashMap();

    private Map<EntryKey, ScheduledFuture> futures = Maps.newConcurrentHashMap();

    private ChannelHandlerContext ctx;

    public void registerCall(short recMessageId, short recMessageType, Runnable task) {
        final EntryKey key = new EntryKey(recMessageId, recMessageType);
        runnables.put(key, task);
        ScheduledFuture future = ctx.executor().schedule(new Runnable() {
            @Override
            public void run() {
                remove(key);
            }
        }, 90, TimeUnit.SECONDS);
        futures.put(key, future);
    }

    public void remove(EntryKey key) {
        runnables.remove(key);
        futures.remove(key);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        this.ctx = ctx;
    }

}
