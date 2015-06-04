package org.eastbar.site.log.dao;

import org.eastbar.comm.log.entity.EmailLog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/3.
 */
@Repository
public interface EmailLogDao extends PagingAndSortingRepository<EmailLog, Long> {
}
