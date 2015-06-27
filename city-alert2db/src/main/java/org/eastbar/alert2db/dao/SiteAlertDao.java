package org.eastbar.alert2db.dao;

import org.eastbar.alert2db.entity.SiteAlert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Repository
public interface SiteAlertDao extends CrudRepository<SiteAlert,Long> {
}