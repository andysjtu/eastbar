package org.eastbar.alertd.dao;

import org.eastbar.alertd.entity.SiteAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Service
@Transactional
public class AlertService {
    @Autowired
    private SiteAlertDao siteAlertDao;

    public void saveSiteAlert(SiteAlert siteAlert){
        siteAlertDao.save(siteAlert);
    }

    public void saveSiteAlet(List<SiteAlert> siteAlertList){
        siteAlertDao.save(siteAlertList);
    }
}
