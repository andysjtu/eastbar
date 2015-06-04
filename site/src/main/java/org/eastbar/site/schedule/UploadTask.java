package org.eastbar.site.schedule;

import org.eastbar.codec.JsonUtil;
import org.eastbar.site.alert.dao.AlertService;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.eastbar.site.log.dao.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Component
public class UploadTask {
    @Autowired
    private AlertService alertService;
    @Autowired
    private LogService logService;
    public void uploadAlert(){
        List<UrlBlockAlert> urlBlockAlertList = alertService.getOldestUrlBlockRecord();
        String url = JsonUtil.toJson(urlBlockAlertList);

    }
}
