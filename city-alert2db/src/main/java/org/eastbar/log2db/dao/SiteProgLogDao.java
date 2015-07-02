package org.eastbar.log2db.dao;

import java.util.List;

import org.eastbar.log2db.entity.SiteProgLog;
import org.springframework.stereotype.Repository;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@MyBatisRepository
public interface SiteProgLogDao {
	
	public void saveListProgHistory(List<SiteProgLog> prog);
}
