package org.eastbar.site.alert.dao;

import org.eastbar.net.alert.entity.IllegalBlockAlert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
//@Repository
public interface IllegalBlockAlertDao extends PagingAndSortingRepository<IllegalBlockAlert, Long> {
}
