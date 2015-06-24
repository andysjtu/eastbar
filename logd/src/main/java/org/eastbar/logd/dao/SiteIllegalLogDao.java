package org.eastbar.logd.dao;

import org.eastbar.logd.entity.SiteIllegalLog;
import org.eastbar.logd.entity.SiteUrlLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Repository
public interface SiteIllegalLogDao extends CrudRepository<SiteIllegalLog,Long> {
}
