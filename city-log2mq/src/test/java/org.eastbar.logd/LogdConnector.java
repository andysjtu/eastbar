package org.eastbar.logd;

import com.google.common.collect.Lists;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.eastbar.codec.*;
import org.eastbar.codec.log.EmailLogMsg;
import org.eastbar.codec.log.InstMsgLogMsg;
import org.eastbar.codec.log.ProgLogMsg;
import org.eastbar.codec.log.UrlLogMsg;
import org.eastbar.net.log.entity.EmailLog;
import org.eastbar.net.log.entity.InstMsgLog;
import org.eastbar.net.log.entity.PrgLog;
import org.eastbar.net.log.entity.UrlLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by AndySJTU on 2015/6/4.
 */
public class LogdConnector {
    public final static Logger logger = LoggerFactory.getLogger(LogdConnector.class);

    private String remoteAddr = "log.nbscreen.com";
//    private String remoteAddr = "localhost";
    private int remotePort = 9021;


    private NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    private volatile Channel remoteChannel;
    private Bootstrap bootstrap;


    private void configBootstrap() {
        bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .remoteAddress(remoteAddr, remotePort)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("logHandler", new LoggingHandler("连接中心端", LogLevel.INFO));
                        pipeline.addLast("gzipDecoder", ZlibCodecFactory.newZlibDecoder());
                        pipeline.addLast("gzipEncoder", ZlibCodecFactory.newZlibEncoder(3));
                        pipeline.addLast("soketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("eastframeDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                    }
                });
    }


    public Channel channel() {
        return this.remoteChannel;
    }

    public boolean isConnected() {
        if (remoteChannel != null) {
            return true;
        }
        return false;
    }


    public void connect() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        ChannelFuture future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("成功连接上Logd服务器");
                    remoteChannel = future.channel();
                    latch.countDown();

                    remoteChannel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            remoteChannel = null;
                        }
                    });
                }
            }
        });
        latch.await();
    }


    public void disconnect() {
        if (remoteChannel.isActive()) {
            remoteChannel.close();
            workerGroup.shutdownGracefully();
        } else {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LogdConnector connector = new LogdConnector();
        connector.configBootstrap();
        connector.connect();
        Thread.sleep(5000);
        if (connector.isConnected()) {
            System.out.println("------------------------");
            Channel channel = connector.channel();
            List<InstMsgLog> logList = Lists.newArrayList();
            for (int i = 0; i < 100; i++) {
                InstMsgLog urlLog = new InstMsgLog();
                urlLog.setId(null);
                urlLog.setCustomerId("320107197902026432");
                urlLog.setCustomerName("梁琳");
                urlLog.setHostIp("192.168.56.34");
                urlLog.setIsBlock(false);
                urlLog.setSiteCode("3101070001");
                urlLog.setStartTime(new Date());
                urlLog.setEndTime(new Date());
                urlLog.setProgAccount("23@qq.com");
                urlLog.setRecordTime(new Date());
                urlLog.setProgType("1");



                urlLog.setRecordTime(new Date());

                logList.add(urlLog);
            }
            InstMsgLogMsg msg = new InstMsgLogMsg(logList);
            channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
        } else {
            System.out.println("没有连上，请检查");
        }
    }
}
