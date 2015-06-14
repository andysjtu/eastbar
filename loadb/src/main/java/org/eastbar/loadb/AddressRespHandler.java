package org.eastbar.loadb;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.assertj.core.util.Maps;
import org.eastbar.codec.*;
import org.eastbar.codec.loadb.AddressResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by AndySJTU on 2015/6/12.
 */
public class AddressRespHandler extends SimpleChannelInboundHandler<SocketMsg> {

    public final static String DEFAULT_HANDLER_NAME = "AddressRespHandler";

    public final static Logger logger = LoggerFactory.getLogger(AddressRespHandler.class);

    private Map<EntryKey, CountDownLatch> locks = Maps.newConcurrentHashMap();

    private Map<EntryKey, ResultWrapper> results = Maps.newConcurrentHashMap();


    public void register(short messageId, short messageType, CountDownLatch latch) {
        locks.put(new EntryKey(messageId, messageType), latch);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short type = msg.getMessageType();
        if (type == SiteMsgType.GEN_RESP.shortValue()) {
            GenResp resp = new GenResp(msg);
            short recMsgId = resp.getRecMessageId();
            short recMsgType = resp.getRecMessageType();
            EntryKey key = new EntryKey(recMsgId, recMsgType);
            CountDownLatch latch = locks.get(key);
            if (latch != null) {
                results.put(key, new ResultWrapper(1, resp.getStatus()));
                latch.countDown();
                return;
            }
        } else if (type == SiteMsgType.ADDRESS_RESP.shortValue()) {//截图
            AddressResp resp = new AddressResp(msg);
            short recMsgId = resp.getRecMessageId();
            short recMsgType = resp.getRecMessageType();
            EntryKey key = new EntryKey(recMsgId, recMsgType);
            Object obj = locks.get(key);
            if (obj != null) {
                results.put(key, new ResultWrapper(2, new DomainAndPort(resp.getDomain(), resp.getPort())));
                CountDownLatch latch = (CountDownLatch) obj;
                latch.countDown();
                return;
            }
        } else {
            logger.info("不是命令响应类型的包");
        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));
    }

    public ResultWrapper getResult(short messageId, short messageType) {
        return results.remove(new EntryKey(messageId, messageType));
    }

    public void unregister(short messageId, short messageType) {
        locks.remove(new EntryKey(messageId, messageType));
    }


    public static class ResultWrapper {
        private final int resultType;
        private final Object result;

        public ResultWrapper(int resultType, Object result) {
            this.result = result;
            this.resultType = resultType;
        }

        public Object getResult() {
            return result;
        }


        public int getResultType() {
            return resultType;
        }


    }

}
