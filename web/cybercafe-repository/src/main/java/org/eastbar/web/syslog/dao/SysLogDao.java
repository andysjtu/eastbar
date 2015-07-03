package org.eastbar.web.syslog.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.syslog.entity.SysLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 15-5-8.
 */
@MyBatisRepository
public interface SysLogDao {
    void add(SysLog sysLog);

    List<SysLog> getAllLog(Map<String, Object> re);
}