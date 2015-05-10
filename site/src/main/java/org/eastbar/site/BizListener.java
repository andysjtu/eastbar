package org.eastbar.site;

import org.eastbar.comm.Listener;
import org.springframework.stereotype.Component;

/**
 * Created by andysjtu on 2015/5/9.
 */
@Component
public class BizListener implements Listener {
    @Override
    public void listen() {
        System.out.println("nothing to do");
    }

    @Override
    public void stopListen() {

    }
}
