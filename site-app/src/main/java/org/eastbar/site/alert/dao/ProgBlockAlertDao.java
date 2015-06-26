package org.eastbar.site.alert.dao;

import org.eastbar.comm.alert.entity.ProgBlockAlert;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@Repository
public interface ProgBlockAlertDao extends PagingAndSortingRepository<ProgBlockAlert,Long>{
}
