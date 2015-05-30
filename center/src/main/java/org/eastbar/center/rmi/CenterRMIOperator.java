package org.eastbar.center.rmi;

import org.springframework.stereotype.Component;

/**
 * Created by AndySJTU on 2015/5/25.
 */
@Component
public class CenterRMIOperator {
    /**
     * 0--->success;
     * 1--->failure
     * 2--->format error
     * 3--->unspport
     *
     * @param siteCode
     * @param hostIp
     * @return
     */
    public int operate(OPERATE_METHOD method,String siteCode, String hostIp) {
        //TODO
        return 0;
    }

    /**
     * 截屏
     * @param siteCode
     * @param hostIp
     * @return
     */
    public byte[] capture(String siteCode, String hostIp) {
        //TODO
        return null;
    }


    public static enum OPERATE_METHOD {
        LOCK, UNLOCK, RESTART, SHUTDOWN;
    }

}
