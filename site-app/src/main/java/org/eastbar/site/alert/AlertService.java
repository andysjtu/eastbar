package org.eastbar.site.alert;

import com.google.common.collect.Lists;
import org.eastbar.net.alert.entity.GeneralAlert;
import org.eastbar.net.alert.entity.IllegalBlockAlert;
import org.eastbar.net.alert.entity.ProgBlockAlert;
import org.eastbar.net.alert.entity.UrlBlockAlert;
import org.eastbar.site.alert.dao.GeneralAlertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Service
@Transactional
public class AlertService {
    public static final int DEFAULT_RECORD_NUM=100;
//    @Autowired
//    private IllegalBlockAlertDao illegalBlockAlertDao;
//    @Autowired
//    private ProgBlockAlertDao progBlockAlertDao;
//    @Autowired
//    private UrlBlockAlertDao urlBlockAlertDao;
    @Autowired
    private GeneralAlertDao generalAlertDao;

//    public void saveUrlBlockAlert(UrlBlockAlert urlBlockAlert) {
//        urlBlockAlertDao.save(urlBlockAlert);
//    }
//
//    public void saveIllegalAlert(IllegalBlockAlert illegalBlockAlert) {
//        illegalBlockAlertDao.save(illegalBlockAlert);
//    }
//
//    public void saveProgBlockAlert(ProgBlockAlert progBlockAlert) {
//        progBlockAlertDao.save(progBlockAlert);
//    }
//
//    public List<UrlBlockAlert> getOldestUrlBlockRecord() {
//        PageRequest request = new PageRequest(0, DEFAULT_RECORD_NUM, Sort.Direction.ASC, "alertTime");
//        Page<UrlBlockAlert> page = urlBlockAlertDao.findAll(request);
//        return Lists.newArrayList(page.getContent());
//    }
//
//    public List<IllegalBlockAlert> getOldestIllegalBlockRecord() {
//        PageRequest request = new PageRequest(0, DEFAULT_RECORD_NUM, Sort.Direction.ASC, "alertTime");
//        Page<IllegalBlockAlert> page = illegalBlockAlertDao.findAll(request);
//
//        return Lists.newArrayList(page.getContent());
//    }
//
//    public List<ProgBlockAlert> getOldestProgBlockRecord() {
//        PageRequest request = new PageRequest(0, DEFAULT_RECORD_NUM, Sort.Direction.ASC, "alertTime");
//        Page<ProgBlockAlert> page = progBlockAlertDao.findAll(request);
//        return Lists.newArrayList(page.getContent());
//    }

    public List<GeneralAlert> getOldestGeneralAlarmRecord() {
        PageRequest request = new PageRequest(0, DEFAULT_RECORD_NUM, Sort.Direction.ASC, "alertTime");
        Page<GeneralAlert> page = generalAlertDao.findAll(request);
        return Lists.newArrayList(page.getContent());
    }

    public void saveGeneralAlert(List<GeneralAlert> alerts) {
        generalAlertDao.save(alerts);
    }

    public void deleteGeneralAlert(List<GeneralAlert> alerts) {
        generalAlertDao.delete(alerts);
    }
}
