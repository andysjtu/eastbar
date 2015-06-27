package org.eastbar.site.policy.dao;

import org.eastbar.site.policy.entity.SitePolicyVersion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/5/20.
 */
@Repository
public interface PolicyVersionDao extends PagingAndSortingRepository<SitePolicyVersion, Integer> {
}
