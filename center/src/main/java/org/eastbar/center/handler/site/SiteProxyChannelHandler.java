package org.eastbar.center.handler.site;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.eastbar.codec.EntryKey;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by AndySJTU on 2015/5/18.
 */
public class SiteProxyChannelHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(SiteProxyChannelHandler.class);


    private Map<EntryKey, Channel> targetChannels = Maps.newConcurrentMap();

    private Set<Short> keySet = Sets.newHashSet();

    public void registerkeys(Set<Short> keySet){
        this.keySet = keySet;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        if(keySet.contains(value)){
//        if (value == ClientMsgType.GEN_RESP.shortValue()
//                ||value==ClientMsgType.QUERY_CLIENT_MODULE.shortValue()
//                || value==ClientMsgType.QUERY_CLIENT_PROCESS.shortValue()
//                ||value==ClientMsgType.CAPTURE_CLIENT.shortValue()) {

            ByteBuf buf = msg.data().content().duplicate();
            short recMessageId = buf.readShort();
            short recMessageType = buf.readShort();
//            logger.info("recMsgId is {},recMsgType is {}",recMessageId,recMessageType);
            EntryKey key = new EntryKey(recMessageId, recMessageType);
            Channel targetChannel = targetChannels.remove(key);
            if (targetChannel != null) {
                msg.changeSiteToCenterAttr();
                targetChannel.writeAndFlush(ReferenceCountUtil.retain(msg));
                return;
            }
        }
        ctx.fireChannelRead(ReferenceCountUtil.retain(msg));


    }

    public void registerTarget(short recMessageId, short recMessageType, Channel targetChannel) {
        EntryKey key = new EntryKey(recMessageId, recMessageType);
//        logger.info("key is  :"+key);
        targetChannels.put(key, targetChannel);
    } 
}
