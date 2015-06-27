package org.eastbar.log2db.dao;

import org.eastbar.log2db.entity.SiteUrlLog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Repository
public interface SiteUrlLogDao extends CrudRepository<SiteUrlLog,Long> {
}
