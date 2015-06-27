package org.eastbar.site.log.handler;

import io.netty.channel.Channel;

/**
 * Created by andyliang on 6/27/15.
 */
public abstract class UploadJob implements Runnable{
    private Channel channel;

    @Override
    public void run() {

    }

    protected abstract void work();
}
