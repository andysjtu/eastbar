package org.eastbar.alert2db;

import org.eastbar.alert2db.dao.AlertService;
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

}
