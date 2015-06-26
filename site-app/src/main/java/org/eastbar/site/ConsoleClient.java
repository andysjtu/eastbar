package org.eastbar.site;

import com.google.common.base.Charsets;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.apache.commons.lang3.StringUtils;
import org.eastbar.codec.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Created by andysjtu on 2015/5/10.
 */
public class ConsoleClient {
    private volatile Channel channel;
    NioEventLoopGroup worker = new NioEventLoopGroup();

    private CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public void connect() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .remoteAddress("localhost", 9999)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("log", new LoggingHandler(LogLevel.INFO));
                        pipeline.addLast("eastFrameDecoder", new EastbarFrameDecoder());
                        pipeline.addLast("socketMsgDecoder", new SocketMsgDecoder());
                        pipeline.addLast("socketMsgEncoder", new SocketMsgEncoder());
                        pipeline.addLast("PrintOutHandler", new ResultHandler(cyclicBarrier));
                    }
                });
        ChannelFuture future = bootstrap.connect();
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    channel = future.channel();
                    channel.closeFuture().addListener(new ChannelFutureListener() {
                        @Override
                        public void operationComplete(ChannelFuture future) throws Exception {
                            channel = null;
                            worker.shutdownGracefully();
                        }
                    });
                } else {
                    worker.shutdownGracefully();
                }
            }
        });
        future.sync();
    }

    private void close() {
        channel.close();
        worker.shutdownGracefully();
    }

    public void readConsole() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入命令：");
        String content = reader.readLine();
        while (true && channel.isActive()) {
            String cmd = StringUtils.trimToNull(content);
            System.out.println("Receive CMD is : {" + cmd + "}");
            if (cmd != null) {
                if ("quit".equals(cmd)) {
                    close();
                    break;
                } else if (cmd.equals("list")) {
                    SocketMsg msg = new SiteCmd.ListTerminalCmd();
                    if (channel != null) {
                        channel.writeAndFlush(msg);
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.equals("status")) {
                    SocketMsg msg = new SiteCmd.StatusTerminalCmd();
                    if (channel != null) {
                        channel.writeAndFlush(msg);
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("lock ")) {
                    String ip = cmd.replace("lock", "").trim();
                    SocketMsg msg = new SiteCmd.LockClientCmd(ip);
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("unlock ")) {
                    String ip = cmd.replace("unlock", "").trim();
                    SocketMsg msg = new SiteCmd.UnlockClientCmd(ip);
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await(5, TimeUnit.SECONDS);
                        System.out.println("xxxxxxxxxxxxxxxxx----------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("query module ")) {
                    String ip = cmd.replace("query module", "").trim();
                    SocketMsg msg = new SiteCmd.QueryClientModuleCmd(ip);
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await(15, TimeUnit.SECONDS);
                        System.out.println("xxxxxxxxxxxxxxxx----------------------");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("query process ")) {
                    String ip = cmd.replace("query process ", "").trim();
                    SocketMsg msg = new SiteCmd.QueryClientProcessCmd(ip);
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await(15, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }

                } else if (cmd.startsWith("capture ")) {
                    String ip = cmd.replace("capture ", "").trim();
                    SocketMsg msg = new SiteCmd.CaptureClientCmd(ip);
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("kill module ")) {
                    String[] ipid = cmd.replace("kill module ", "").trim().split(" ");
                    SocketMsg msg = new SiteCmd.KillClientModule(ipid[0].trim(), ipid[1].trim());
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                } else if (cmd.startsWith("kill ")) {
                    String[] ipid = cmd.replace("kill ", "").trim().split(" ");
                    SocketMsg msg = new SiteCmd.KillClientProcess(ipid[0].trim(), ipid[1].trim());
                    if (channel != null) {
                        channel.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                    }
                    try {
                        cyclicBarrier.await(5, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.print("请输入命令：");
            content = reader.readLine();
        }

    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ConsoleClient client = new ConsoleClient();
        client.connect();
        if (client.getChannel() != null) {
            client.readConsole();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    public static class ResultHandler extends SimpleChannelInboundHandler<SocketMsg> {

        private final CyclicBarrier barrier;

        public ResultHandler(CyclicBarrier cyclicBarrier) {
            this.barrier = cyclicBarrier;
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, SocketMsg msg) throws Exception {
            if(msg.getMessageType()==SiteMsgType.TERM_STATUS.shortValue()){
                TermReportMsg termReportMsg = new TermReportMsg(msg);
                System.out.println(termReportMsg.getReport());
                return;
            }else if(msg.getMessageType()==SiteMsgType.POLICY_STATUS.shortValue()){
                SiteReportMsg siteReportMsg = new SiteReportMsg(msg);
                System.out.println(siteReportMsg.getReport());
                return;
            }
            try {
                if (msg.getMessageType() == SiteMsgType.LIST.shortValue()) {
                    System.out.println(msg.data().content().toString(Charsets.UTF_8));
                }
                if (msg.getMessageType() == SiteMsgType.STATUS.shortValue()) {
                    System.out.println(msg.data().content().toString(Charsets.UTF_8));
                } else if (msg.getMessageType() == SiteMsgType.GEN_RESP.shortValue()) {
                    ByteBuf buf = msg.data().content();
                    buf.readShort();
                    buf.readShort();
                    System.out.println("操作结果是 : " + buf.readByte());
                } else if (msg.getMessageType() == ClientMsgType.QUERY_CLIENT_PROCESS.shortValue()) {
                    ByteBuf buf = msg.data().content();
                    buf.readShort();
                    buf.readShort();
                    System.out.println("操作结果是 : " + buf.toString(Charsets.UTF_8));
                } else if (msg.getMessageType() == ClientMsgType.QUERY_CLIENT_MODULE.shortValue()) {
                    ByteBuf buf = msg.data().content();
                    buf.readShort();
                    buf.readShort();
//                int length = buf.readableBytes();
                    System.out.println("操作结果是 : " + buf.toString(Charsets.UTF_8));
                } else if (msg.getMessageType() == ClientMsgType.CAPTURE_CLIENT.shortValue()) {
                    ByteBuf buf = msg.data().content();
                    buf.readShort();
                    buf.readShort();
                    byte[] content = new byte[buf.readableBytes()];
                    buf.readBytes(content);

                    BufferedImage imageBuffer = ImageIO.read(new ByteArrayInputStream(content));
                    Path path = Files.createTempFile("TEST", ".jpeg");
                    System.out.println("path is : " + path);
                    ImageIO.write(imageBuffer, "JPEG", path.toFile());
                }
            } finally {
                try {
                    barrier.await(1, TimeUnit.SECONDS);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    barrier.reset();
                }
            }

        }
    }

    public static class ShutdownHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.channel().writeAndFlush(Unpooled.wrappedBuffer("quit\n".getBytes()));
        }
    }
}
