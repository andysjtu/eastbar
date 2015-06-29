package org.eastbar.city.console;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.eastbar.codec.SocketMsg;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by andyliang on 6/29/15.
 */
public class ConsoleCmdHandler extends SimpleChannelInboundHandler<SocketMsg> {
    private Executor executor = Executors.newSingleThreadExecutor();
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SocketMsg socketMsg) throws Exception {

    }

    private void processCmd(ChannelHandlerContext ctx){
        ConsoleUtil consoleUtil = new ConsoleUtil(System.in);
        try {
            String line = consoleUtil.readLine().trim();
            if("quit".equals(line)){
                ctx.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
