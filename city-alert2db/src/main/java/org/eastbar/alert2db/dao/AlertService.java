package org.eastbar.alert2db.dao;

import org.eastbar.alert2db.entity.SiteAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Service("alertService")
@Transactional
public class AlertService {
    @Autowired
    private SiteAlertDao siteAlertDao;

    public void saveSiteAlet(List<SiteAlert> siteAlertList){
        siteAlertDao.saveListAlarmHistory(siteAlertList);
    }
}
