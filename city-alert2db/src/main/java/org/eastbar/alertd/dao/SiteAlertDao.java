package org.eastbar.alertd.dao;

import org.eastbar.alertd.entity.SiteAlert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Repository
public interface SiteAlertDao extends CrudRepository<SiteAlert,Long> {
}
