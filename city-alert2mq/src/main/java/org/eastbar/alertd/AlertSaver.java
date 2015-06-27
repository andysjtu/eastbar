package org.eastbar.alertd;

import org.eastbar.alertd.dao.AlertService;
import org.eastbar.net.alert.entity.IllegalBlockAlert;
import org.eastbar.net.alert.entity.ProgBlockAlert;
import org.eastbar.net.alert.entity.UrlBlockAlert;
import org.eastbar.logd.dao.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by AndySJTU on 2015/6/4.
 */

public class AlertSaver {
    public final static Logger logger = LoggerFactory.getLogger(AlertSaver.class);

    @Autowired
    private AlertService alertService;
    private final ExecutorService service = Executors.newFixedThreadPool(50);
    public void saveUrlAlert(List<UrlBlockAlert> urlBlockAlertList){
        //TODO
    }

    public void saveProgAlert(List<ProgBlockAlert> progBlockAlertList){
        //TODO
    }

    public void saveIllegalAlert(List<IllegalBlockAlert> illegalLogList){
        //TODO
    }
}
