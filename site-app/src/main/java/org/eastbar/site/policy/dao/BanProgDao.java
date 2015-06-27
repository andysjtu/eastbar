package org.eastbar.site.policy.dao;

import org.eastbar.site.policy.entity.BanProg;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/5/19.
 */
@Repository
public interface BanProgDao extends PagingAndSortingRepository<BanProg, Integer> {
}
