package org.eastbar.site.bizproxy;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizProxyMain {
    public static void main(String[] args) {
        BizProxyServer bizProxyServer = new BizProxyServer();
        BizOuterListener outerListener = new BizOuterListener(bizProxyServer);
        BizInnerListener innerListener = new BizInnerListener(bizProxyServer);
        try {
            outerListener.doListen();
            innerListener.doListen();
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }
}
