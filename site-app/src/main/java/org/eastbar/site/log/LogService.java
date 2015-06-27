package org.eastbar.site.log;

import org.eastbar.net.log.entity.*;
import org.eastbar.site.log.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/3.
 */
@Service
@Transactional
public class LogService {
    public static final int DEFAULT_RECORDS_NUM = 100;
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

    public List<EmailLog> getOldestEmailRecords() {
        Pageable pageable = new PageRequest(0, DEFAULT_RECORDS_NUM, Sort.Direction.DESC, "recordTime");
        return emailLogDao.findAll(pageable).getContent();
    }

    public void deleteEmailLog(List<EmailLog> emailLogList) {
        emailLogDao.delete(emailLogList);
    }

    public List<PrgLog> getOldestProgRecords() {
        Pageable pageable = new PageRequest(0, DEFAULT_RECORDS_NUM, Sort.Direction.DESC, "recordTime");
        return prgLogDao.findAll(pageable).getContent();
    }

    public List<UrlLog> getOldestURLRecords() {
        Pageable pageable = new PageRequest(0, DEFAULT_RECORDS_NUM, Sort.Direction.DESC, "recordTime");
        return urlLogDao.findAll(pageable).getContent();
    }

    public void deleteUrlLog(List<UrlLog> emailLogList) {
        urlLogDao.delete(emailLogList);
    }

    public List<InstMsgLog> getOldestInstMsgRecords() {
        Pageable pageable = new PageRequest(0, DEFAULT_RECORDS_NUM, Sort.Direction.DESC, "recordTime");
        return instMsgLogDao.findAll(pageable).getContent();
    }

    public void deleteInstMsgLog(List<InstMsgLog> emailLogList) {
        instMsgLogDao.delete(emailLogList);
    }

    public List<IllegalLog> getOldestIllegalLogRecords() {
        Pageable pageable = new PageRequest(0, DEFAULT_RECORDS_NUM, Sort.Direction.DESC, "recordTime");
        return illegalLogDao.findAll(pageable).getContent();
    }

    public void deleteIllegalLog(List<IllegalLog> emailLogList) {
        illegalLogDao.delete(emailLogList);
    }

    public void deleteProgLog(List<PrgLog> prgLogList) {
        prgLogDao.delete(prgLogList);
    }

}
