package org.eastbar.center.rmi;

import io.netty.channel.*;
import org.eastbar.codec.CenterCmd;
import org.eastbar.codec.GenResp;
import org.eastbar.codec.SocketMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Pipeline;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/5/25.
 */
@Component
public class CenterOperator {



    private CenterConnector connector;

    @PostConstruct
    public void init() {
        connector = new CenterConnector();
        connector.setRemoteAddr("localhost");
        connector.setLocalPort(29999);
        connector.configBootstrap();
        connector.connect();
    }

    public int lock(String siteCode, String hostIp) {
        if (connector.isConnected()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.LockClientCmd(siteCode, hostIp);
            System.out.println(msg.getHost());
            CenterCmdRespHandler handler = getCenterCmdRespHandler();
            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            connector.channel().writeAndFlush(msg).addListener(new ChannelFutureListener() {
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
        if (connector.isConnected()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.CaptureClientCmd(siteCode, hostIp);
            CenterCmdRespHandler handler = getCenterCmdRespHandler();

            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            connector.channel().writeAndFlush(msg).addListener(new ChannelFutureListener() {
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

    private CenterCmdRespHandler getCenterCmdRespHandler() {
        ChannelPipeline pipeline = connector.channel().pipeline();
        return (CenterCmdRespHandler) pipeline.get(CenterCmdRespHandler.DEFAULT_HANDLER_NAME);
    }


    public int unlock(String siteCode, String hostIp) {
        if (connector.isConnected()) {
            final CountDownLatch latch = new CountDownLatch(1);
            SocketMsg msg = new CenterCmd.UnlockClientCmd(siteCode, hostIp);
            System.out.println(msg.getHost());
            CenterCmdRespHandler handler = getCenterCmdRespHandler();
            handler.register(msg.getMessageId(), msg.getMessageType(), latch);
            connector.channel().writeAndFlush(msg).addListener(new ChannelFutureListener() {
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
