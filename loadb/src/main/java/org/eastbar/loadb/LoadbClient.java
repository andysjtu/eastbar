package org.eastbar.loadb;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPipeline;
import org.eastbar.codec.loadb.AddressReq;
import org.eastbar.codec.loadb.AddressResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/12.
 */
@Component
public class LoadbClient {

    @Autowired
    private LoadbConnector connector;

    @Value("${sitecode")
    private String siteCode;

    public void connnect() throws InterruptedException {
        connector.connect();
    }

    public DomainAndPort getServerAddr(String type) {
        if (connector.isConnected()) {
            final CountDownLatch latch = new CountDownLatch(2);
            AddressReq req = new AddressReq(siteCode, type);
            connector.channel().writeAndFlush(req).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (!future.isSuccess()) {
                        latch.countDown();
                    }
                }
            });
            ChannelPipeline pipeline = connector.channel().pipeline();
            AddressRespHandler handler = (AddressRespHandler) pipeline.get(AddressRespHandler.DEFAULT_HANDLER_NAME);

            handler.register(req.getMessageId(), req.getMessageType(), latch);
            try {
                latch.await(2, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failure");
            }

            AddressRespHandler.ResultWrapper resultWrapper = handler.getResult(req.getMessageId(), req.getMessageType());
            if (resultWrapper != null) {
                if (resultWrapper.getResultType() == 2) {
                    return (DomainAndPort) resultWrapper.getResult();
                }
            }
        }
        throw new RuntimeException("Failure");
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public void close() {
        connector.disconnect();
    }



    public static void main(String[] args) throws InterruptedException {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
               "applicationContext-loadb.xml"
        });


        LoadbClient client = context.getBean(LoadbClient.class);
        client.setSiteCode("43001001");

        client.connnect();
        Thread.sleep(1000);
        System.out.println(client.getServerAddr("alert"));

        client.close();
    }
}
