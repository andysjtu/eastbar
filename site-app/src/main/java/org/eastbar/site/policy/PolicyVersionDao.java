package org.eastbar.site.policy;

import org.eastbar.site.policy.entity.SitePolicyVersion;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public interface PolicyVersionDao extends PagingAndSortingRepository<SitePolicyVersion, Integer> {
}
