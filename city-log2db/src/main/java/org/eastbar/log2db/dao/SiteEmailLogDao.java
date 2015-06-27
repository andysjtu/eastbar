package org.eastbar.log2db.dao;

import org.eastbar.log2db.entity.SiteEmailLog;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Repository
public interface SiteEmailLogDao extends CrudRepository<SiteEmailLog,Long> {
}
