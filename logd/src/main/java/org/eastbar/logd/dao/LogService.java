package org.eastbar.logd.dao;

import org.eastbar.logd.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Service
@Transactional
public class LogService {
    @Autowired
    private SiteUrlLogDao siteUrlLogDao;
    @Autowired
    private SiteIllegalLogDao siteIllegalLogDao;
    @Autowired
    private SiteEmailLogDao siteEmailLogDao;
    @Autowired
    private SiteProgLogDao siteProgLogDao;

    private SiteInstMsgLogDao siteInstMsgLogDao;

    public void saveSiteUrlLog(List<SiteUrlLog> siteUrlLogList) {
        siteUrlLogDao.save(siteUrlLogList);
    }

    public void saveIllegalLog(List<SiteIllegalLog> siteIllegalLogs) {
        siteIllegalLogDao.save(siteIllegalLogs);
    }

    public void saveEmailLog(List<SiteEmailLog> siteEmailLogs) {
        siteEmailLogDao.save(siteEmailLogs);
    }

    public void saveProgLog(List<SiteProgLog> siteProgLogs) {
        siteProgLogDao.save(siteProgLogs);
    }

    public void saveInstMsgLog(List<SiteInstMsgLog> siteInstMsgLogs) {
        siteInstMsgLogDao.save(siteInstMsgLogs);
    }
}
