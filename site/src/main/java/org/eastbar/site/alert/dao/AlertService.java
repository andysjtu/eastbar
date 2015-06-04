package org.eastbar.site.alert.dao;

import com.google.common.collect.Lists;
import org.eastbar.comm.alert.entity.IllegalBlockAlert;
import org.eastbar.comm.alert.entity.ProgBlockAlert;
import org.eastbar.comm.alert.entity.UrlBlockAlert;
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
    @Autowired
    private IllegalBlockAlertDao illegalBlockAlertDao;
    @Autowired
    private ProgBlockAlertDao progBlockAlertDao;
    @Autowired
    private UrlBlockAlertDao urlBlockAlertDao;

    public void saveUrlBlockAlert(UrlBlockAlert urlBlockAlert) {
        urlBlockAlertDao.save(urlBlockAlert);
    }

    public void saveIllegalAlert(IllegalBlockAlert illegalBlockAlert) {
        illegalBlockAlertDao.save(illegalBlockAlert);
    }

    public void saveProgBlockAlert(ProgBlockAlert progBlockAlert) {
        progBlockAlertDao.save(progBlockAlert);
    }

    public List<UrlBlockAlert> getOldestUrlBlockRecord() {
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "alertTime");
        Page<UrlBlockAlert> page = urlBlockAlertDao.findAll(request);
        urlBlockAlertDao.delete(page.getContent());
        return Lists.newArrayList(page.getContent());
    }

    public List<IllegalBlockAlert> getOldestIllegalBlockRecord() {
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "alertTime");
        Page<IllegalBlockAlert> page = illegalBlockAlertDao.findAll(request);

        illegalBlockAlertDao.delete(page.getContent());
        return Lists.newArrayList(page.getContent());
    }

    public List<ProgBlockAlert> getOldestProgBlockRecord() {
        PageRequest request = new PageRequest(0, 10, Sort.Direction.ASC, "alertTime");
        Page<ProgBlockAlert> page = progBlockAlertDao.findAll(request);
        progBlockAlertDao.delete(page.getContent());
        return Lists.newArrayList(page.getContent());
    }
}
