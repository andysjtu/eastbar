package org.eastbar.site.log.dao;

import org.eastbar.net.log.entity.IllegalLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/3.
 */
@Repository
public interface IllegalLogDao extends PagingAndSortingRepository<IllegalLog,Long> {
}
