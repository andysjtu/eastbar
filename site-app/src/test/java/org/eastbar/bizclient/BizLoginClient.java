package org.eastbar.bizclient;

import io.netty.channel.*;

/**
 * Created by AndySJTU on 2015/6/17.
 */
public class BizLoginClient extends AbstractClient{


    public static void main(String[] args) {
        BizLoginClient bizClient = new BizLoginClient();
        bizClient.connect();
    }

    @Override
    protected void registerHandler(ChannelPipeline pipeline) {
        pipeline.addLast(new MockBizHandler());
    }
}
