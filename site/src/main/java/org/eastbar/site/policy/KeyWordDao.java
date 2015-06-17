package org.eastbar.site.policy;

import org.eastbar.site.policy.entity.KeyWord;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by AndySJTU on 2015/5/19.
 */
public interface KeyWordDao extends PagingAndSortingRepository<KeyWord, Integer> {
}