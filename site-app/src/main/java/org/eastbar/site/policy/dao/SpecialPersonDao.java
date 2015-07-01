package org.eastbar.site.policy.dao;

import org.eastbar.site.policy.entity.SpecialPerson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/8.
 */
@Repository
public interface SpecialPersonDao extends PagingAndSortingRepository<SpecialPerson, Integer> {
    public SpecialPerson findBycertId(String certId);
}
