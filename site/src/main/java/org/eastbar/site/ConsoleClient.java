package org.eastbar.site;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.lang3.StringUtils;
import sun.plugin2.main.server.ResultHandler;

import java.util.Scanner;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleClient {
    private volatile  Channel channel;
    NioEventLoopGroup worker = new NioEventLoopGroup();

    public void connect() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .remoteAddress("localhost",9999)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("log",new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast("stringEncoder",new StringEncoder());
                        pipeline.addLast("lineFrameDecoder",new LineBasedFrameDecoder(1000));
                        pipeline.addLast("stringDecoder",new StringDecoder());
                        pipeline.addLast("stringResultHandler",new ResultHandler());
                    }
                });
        ChannelFuture future =  bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    channel = future.channel();
                    channel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            worker.shutdownGracefully();
                        }
                    });
                }else{
                    worker.shutdownGracefully();
                }
            }
        });
        future.sync();
    }

    private void close(){
        channel.close();
        worker.shutdownGracefully();
    }

    public void readConsole(){
        Scanner scanner = new Scanner(System.in);
        String content = scanner.nextLine();
        while(true&&channel.isActive()) {
            String cmd = StringUtils.trimToNull(content);
            if(cmd!=null) {
                if("quit".equals(cmd)){
                    close();
                    break;
                }
                if (channel.isActive()) {
                    channel.writeAndFlush(content + "\n");
                } else {

                    break;
                }
            }
            content = scanner.nextLine();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ConsoleClient client = new ConsoleClient();
        client.connect();
        if(client.getChannel()!=null) {
            client.readConsole();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public static class ResultHandler extends SimpleChannelInboundHandler<String>{

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println(">"+msg);
            System.out.print(">");
        }
    }

    public static class ShutdownHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.channel().writeAndFlush(Unpooled.wrappedBuffer("quit\n".getBytes()));
        }
    }
}
