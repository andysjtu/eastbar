package org.eastbar.site.policy.dao;

import org.eastbar.site.policy.entity.BanUrl;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/5/19.
 */
@Repository
public interface BanUrlDao extends PagingAndSortingRepository<BanUrl, Integer> {
}
