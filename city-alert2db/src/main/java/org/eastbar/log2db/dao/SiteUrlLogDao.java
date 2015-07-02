package org.eastbar.log2db.dao;

import java.util.List;

import org.eastbar.log2db.entity.SiteUrlLog;

/**
 * Created by AndySJTU on 2015/6/4.
 */
@MyBatisRepository
public interface SiteUrlLogDao {
	
	public void saveListUrlHistory(List<SiteUrlLog> url);
	
}
