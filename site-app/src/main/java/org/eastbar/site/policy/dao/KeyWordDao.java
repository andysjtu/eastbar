package org.eastbar.site.policy.dao;

import org.eastbar.site.policy.entity.KeyWord;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/5/19.
 */
@Repository
public interface KeyWordDao extends PagingAndSortingRepository<KeyWord, Integer> {
}
