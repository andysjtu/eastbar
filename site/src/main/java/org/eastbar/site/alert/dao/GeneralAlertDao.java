package org.eastbar.site.alert.dao;

import org.eastbar.comm.alert.entity.GeneralAlert;
import org.eastbar.comm.alert.entity.IllegalBlockAlert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/8.
 */
@Repository
public interface GeneralAlertDao extends PagingAndSortingRepository<GeneralAlert, Long> {
}
