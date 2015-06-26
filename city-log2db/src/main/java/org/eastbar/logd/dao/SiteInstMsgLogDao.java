package org.eastbar.logd.dao;

import org.eastbar.logd.entity.SiteInstMsgLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Repository
public interface SiteInstMsgLogDao extends CrudRepository<SiteInstMsgLog,Long> {
}
