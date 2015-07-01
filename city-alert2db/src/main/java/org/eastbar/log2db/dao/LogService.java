package org.eastbar.log2db.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.eastbar.log2db.entity.SiteEmailLog;
import org.eastbar.log2db.entity.SiteIllegalLog;
import org.eastbar.log2db.entity.SiteInstMsgLog;
import org.eastbar.log2db.entity.SiteProgLog;
import org.eastbar.log2db.entity.SiteUrlLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private SiteInstMsgLogDao siteInstMsgLogDao;

    public void saveSiteUrlLog(List<SiteUrlLog> siteUrlLogList) {
        siteUrlLogDao.saveListUrlHistory(siteUrlLogList);
    }

    public void saveIllegalLog(List<SiteIllegalLog> siteIllegalLogs) {
        siteIllegalLogDao.saveListLegalHistory(siteIllegalLogs);
    }

    public void saveEmailLog(List<SiteEmailLog> siteEmailLogs) {
        siteEmailLogDao.saveListMailHistory(siteEmailLogs);
    }

    public void saveProgLog(List<SiteProgLog> siteProgLogs) {
        siteProgLogDao.saveListProgHistory(siteProgLogs);
    }

    public void saveInstMsgLog(List<SiteInstMsgLog> siteInstMsgLogs) {
        siteInstMsgLogDao.saveListInstantMessageHistory(siteInstMsgLogs);
    }
}
