package org.eastbar.codec;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * Created by AndySJTU on 2015/5/25.
 */
@ChannelHandler.Sharable
public class ProxyChannelHandler extends SimpleChannelInboundHandler<SocketMsg> {
    public final static Logger logger= LoggerFactory.getLogger(ProxyChannelHandler.class);

    public final static String HANDLER_NAME = "proxyHandler";
    private Map<EntryKey, Channel> targetChannels = Maps.newConcurrentMap();

    private Set<Short> keySet = Sets.newConcurrentHashSet();

    public void addKey(short key) {
        keySet.add(key);
    }

    public void registerKeys(Set<Short> keySet) {
        this.keySet = keySet;
    }

    public ProxyChannelHandler(Set<Short> keySet) {
        this.keySet = keySet;
    }

    public ProxyChannelHandler(){
        keySet.add(SiteMsgType.GEN_RESP.shortValue());
        keySet.add(ClientMsgType.CAPTURE_CLIENT.shortValue());
        keySet.add(ClientMsgType.QUERY_CLIENT_MODULE.shortValue());
        keySet.add(ClientMsgType.QUERY_CLIENT_PROCESS.shortValue());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
        short value = msg.getMessageType();
        logger.info("keySet is : "+keySet);
        if(value==SiteMsgType.GEN_RESP.shortValue()||value==ClientMsgType.CAPTURE_CLIENT.shortValue()){

            ByteBuf buf = msg.data().content().duplicate();
            short recMessageId = buf.readShort();
            short recMessageType = buf.readShort();
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
        targetChannels.put(key, targetChannel);
    }
}
