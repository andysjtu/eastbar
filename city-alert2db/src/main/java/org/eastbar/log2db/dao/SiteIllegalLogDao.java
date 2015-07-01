package org.eastbar.log2db.dao;

import java.util.List;

import org.eastbar.log2db.entity.SiteIllegalLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@MyBatisRepository
public interface SiteIllegalLogDao {
	
	public void saveListLegalHistory(List<SiteIllegalLog> legal);
}
