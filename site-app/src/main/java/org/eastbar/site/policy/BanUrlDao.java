package org.eastbar.site.policy;

import org.eastbar.site.policy.entity.BanUrl;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by AndySJTU on 2015/5/19.
 */
public interface BanUrlDao extends PagingAndSortingRepository<BanUrl, Integer> {
}