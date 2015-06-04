package org.eastbar.site.alert.dao;

import org.eastbar.comm.alert.entity.UrlBlockAlert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Repository
public interface UrlBlockAlertDao extends PagingAndSortingRepository<UrlBlockAlert,Long> {

}
