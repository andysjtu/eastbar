package org.eastbar.site.policy;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.nio.cs.ext.GBK;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by AndySJTU on 2015/5/12.
 */
public class PolicyDldHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    public final static Logger logger = LoggerFactory.getLogger(PolicyDldHandler.class);


    private final PolicyManager manager;

    public PolicyDldHandler(PolicyManager manager) {
        this.manager = manager;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        if (!request.getDecoderResult().isSuccess()) {
            logger.info("请求错误,请检查");
            sendError(ctx, BAD_REQUEST);
        }


        final String uri = request.getUri();

        logger.info("uri is : " + uri);

        if ("/url".equals(uri)) {
            sendUrl(ctx);
        } else if ("/program".equals(uri)) {
            sendProgram(ctx);
        } else if ("/keywords".equals(uri)) {
            sendKeywords(ctx);
        } else
            sendError(ctx, HttpResponseStatus.NOT_FOUND);


    }

    private void sendKeywords(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        String keywords = manager.getBanKeywordString();
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK);


        byte[] content = keywords.getBytes("GBK");
        setContentLength(response, content.length);
        response.content().writeBytes(content);

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void sendProgram(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        String progStr = manager.getBanProgString();
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK);



        byte[] content = progStr.getBytes("GBK");
        setContentLength(response, content.length);
        response.content().writeBytes(content);

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void sendUrl(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        String urlString = manager.getBanUrlString();
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK);



        byte[] content = urlString.getBytes("GBK");
        setContentLength(response, content.length);
        response.content().writeBytes(content);

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void sendFile(ChannelHandlerContext ctx, String fileName) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, OK);
        File file = new File(fileName);
        logger.debug("file path is : " + file.getAbsolutePath());


        long fileLength = file.length();
        try {

            byte[] content = Files.toByteArray(file);
            setContentLength(response, fileLength);
            response.content().writeBytes(content);

        } catch (IOException e) {
            logger.warn("ioExeption : ", e);
        }
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    private void setContentLength(HttpResponse response, long fileLength) {
        response.headers().set(CONTENT_LENGTH, fileLength);
        response.headers().set(CONTENT_TYPE, "text/plain;charset=GBK");
        //application/octet-stream;attachment=file.txt
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        String responseContent = "Failure : " + status.toString() + "\r\n";
        ByteBuf buf = Unpooled.copiedBuffer(responseContent, UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, buf);
        response.headers().set(CONTENT_TYPE, "text/plain; charset=utf8");

        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }
}
