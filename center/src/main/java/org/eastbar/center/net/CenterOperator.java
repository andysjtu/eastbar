package org.eastbar.center.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import org.eastbar.codec.CenterCmd;
import org.eastbar.codec.CenterCmdRespHandler;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.SocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/25.
 */
@Component
public class CenterOperator {

    @Autowired
    private CenterHub hub;

    @PostConstruct
    public void init() {

    }

    public int lock(String siteCode, String hostIp) {
        Channel channel = hub.getCenterChannelBySiteCode(siteCode);
        if (channel!=null&&channel.isActive()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.LockClientCmd(siteCode, hostIp);
            System.out.println(msg.getHost());
            CenterCmdRespHandler handler = getCenterCmdRespHandler(channel);
            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        latch.countDown();
                    }
                }
            });
            try {
                latch.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            CenterCmdRespHandler.ResultWrapper obj = handler.getResult(msg.getMessageId(), msg.getMessageType());
            if (obj != null) {
                if (obj.getResultType() == 1) {
                    return ((GenResp.Status) obj.getResult()).byteValue();
                }
            }
        }
        return 1;
    }

    public byte[] capture(String siteCode, String hostIp) {
        Channel channel = hub.getCenterChannelBySiteCode(siteCode);
        if (channel!=null&&channel.isActive()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.CaptureClientCmd(siteCode, hostIp);
            CenterCmdRespHandler handler = getCenterCmdRespHandler(channel);

            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        latch.countDown();
                    }
                }
            });
            try {
                latch.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            CenterCmdRespHandler.ResultWrapper obj = handler.getResult(msg.getMessageId(), msg.getMessageType());
            if (obj != null) {
                if(obj.getResultType()==2) {
                    return (byte[])obj.getResult();
                }
            }
        }
        throw new RuntimeException("FAILURE");
    }

    private CenterCmdRespHandler getCenterCmdRespHandler(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        return (CenterCmdRespHandler) pipeline.get(CenterCmdRespHandler.DEFAULT_HANDLER_NAME);
    }


    public int unlock(String siteCode, String hostIp) {
        Channel channel = hub.getCenterChannelBySiteCode(siteCode);
        if (channel!=null&&channel.isActive()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.UnlockClientCmd(siteCode, hostIp);
            System.out.println(msg.getHost());
            CenterCmdRespHandler handler = getCenterCmdRespHandler(channel);
            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            channel.writeAndFlush(msg).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        latch.countDown();
                    }
                }
            });
            try {
                latch.await(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
            CenterCmdRespHandler.ResultWrapper obj = handler.getResult(msg.getMessageId(), msg.getMessageType());
            if (obj != null) {
                if (obj.getResultType() == 1) {
                    return ((GenResp.Status) obj.getResult()).byteValue();
                }
            }
        }
        return 1;
    }
}
