package org.eastbar.site.policy;

import org.eastbar.site.policy.entity.SpecialPerson;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by AndySJTU on 2015/6/8.
 */
public interface SpecialPersonDao extends PagingAndSortingRepository<SpecialPerson, Integer> {
}
