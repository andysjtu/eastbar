package org.eastbar.site.loadb;

import io.netty.channel.*;
import io.netty.util.concurrent.Promise;
import org.eastbar.codec.loadb.AddressReq;
import org.eastbar.net.DomainAndPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by AndySJTU on 2015/6/12.
 */

public class LoadbClient implements InitializingBean {
    public final static Logger logger = LoggerFactory.getLogger(LoadbClient.class);

    private LoadbConnector connector;

    private String siteCode;

    private volatile ChannelFuture connectFuture;

    private volatile boolean isStarted = false;


    public void connect() throws InterruptedException {
        if (!isStarted) {
            isStarted = true;
            connectFuture = connector.connect();
            connectFuture.sync();
        }
    }

    public DomainAndPort getServerAddr(final String type) throws InterruptedException {

        if (connectFuture.isSuccess()) {
            ChannelPipeline pipeline = connectFuture.channel().pipeline();
            AddressReq req = new AddressReq(siteCode, type);
//            System.out.println("req messageId is " + req.getMessageId());
//            System.out.println("req messageType is " + req.getMessageType());
            ClientHandler handler = new ClientHandler(req, connectFuture.channel().eventLoop());
            pipeline.addLast(handler);

            Promise promise = handler.getPromise();
            promise.await(60, TimeUnit.SECONDS);
            if (promise.isSuccess()) {
                try {
                    return (DomainAndPort) promise.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                throw new RuntimeException(promise.cause());
            }

        }
        throw new RuntimeException("xx");
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

    public LoadbConnector getConnector() {
        return connector;
    }

    @Autowired
    public void setConnector(LoadbConnector connector) {
        this.connector = connector;
    }

    public static void main(String[] args) throws InterruptedException {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
                "applicationContext-loadb-client.xml"
        });


        LoadbClient client = context.getBean(LoadbClient.class);
        client.setSiteCode("43001001");

        client.connect();

        System.out.println(client.getServerAddr("alert"));
        System.out.println(client.getServerAddr("status"));
        System.out.println(client.getServerAddr("log"));
        System.out.println(client.getServerAddr("capture"));


        client.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (connector == null) throw new Exception("connector connot be null");
    }
}
