package org.eastbar.site.log.dao;

import org.eastbar.comm.log.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AndySJTU on 2015/6/3.
 */
@Service
@Transactional
public class LogService {
    @Autowired
    private EmailLogDao emailLogDao;
    @Autowired
    private InstMsgLogDao instMsgLogDao;
    @Autowired
    private IllegalLogDao illegalLogDao;
    @Autowired
    private PrgLogDao prgLogDao;
    @Autowired
    private UrlLogDao urlLogDao;

    public void saveEmailLog(EmailLog log) {
        emailLogDao.save(log);
    }

    public void saveIllegalLog(IllegalLog illegalLog) {
        illegalLogDao.save(illegalLog);
    }

    public void saveUrlLog(UrlLog urlLog) {
        urlLogDao.save(urlLog);
    }

    public void savePrgLog(PrgLog prgLog) {
        prgLogDao.save(prgLog);
    }

    public void saveInstMsgLog(InstMsgLog instMsgLog) {
        instMsgLogDao.save(instMsgLog);
    }
}
