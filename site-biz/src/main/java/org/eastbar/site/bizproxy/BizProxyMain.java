package org.eastbar.site.bizproxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AndySJTU on 2015/6/15.
 */
public class BizProxyMain {
    public final static Logger logger= LoggerFactory.getLogger(BizProxyMain.class);

    public static void main(String[] args) {
        BizProxyServer bizProxyServer = new BizProxyServer();
        BizOuterListener outerListener = new BizOuterListener(bizProxyServer);
        BizInnerListener innerListener = new BizInnerListener(bizProxyServer);
        try {
            outerListener.doListen();
            innerListener.doListen();
            logger.info("启动业务代理服务成功");
        } catch (Throwable t) {
            logger.warn("启动业务代理服务失败");
            t.printStackTrace();
        }

    }
}
