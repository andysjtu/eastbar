package org.eastbar.center;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.assertj.core.util.Maps;
import org.eastbar.codec.SocketMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by andysjtu on 2015/5/10.
 */
@Component
public class Center {
    
    public final static Logger logger= LoggerFactory.getLogger(Center.class);
    

    private Map<String,Channel> siteChannels = Maps.newConcurrentHashMap();

    private Map<String,VSite> sites = Maps.newConcurrentHashMap();

    private ChannelFutureListener closeListener = new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture future) throws Exception {

        }
    };


    public void disconnectAllSites() {
        Collection<Channel> channelList = siteChannels.values();
        for(Channel channel: channelList){
            //FIXME may be send close cmd,then remote close channel
            channel.close();
        }
    }

    public boolean registerSite(final String siteCode,Channel siteChannel){
        if(!existDuplicateSite(siteCode)) {
            siteChannels.put(siteCode, siteChannel);
            //TODO init vsite
            siteChannel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    siteChannels.remove(siteCode);
                    sites.get(siteCode).offline();
                }
            });
            return true;
        }
        logger.warn("该场所端siteCode:{} 存在，不允许重复连接，请检查",siteCode);
        return false;
    }

    private boolean existDuplicateSite(String siteCode){
        if(siteChannels.containsKey(siteCode))return true;
        return false;
    }


    public Channel findSiteChannel(String siteCode){
        return siteChannels.get(siteCode);
    }


    public void sendLockCmd(String siteCode){
        Channel siteChannel = siteChannels.get(siteCode);
        if(siteChannel!=null){

        }
    }

}
