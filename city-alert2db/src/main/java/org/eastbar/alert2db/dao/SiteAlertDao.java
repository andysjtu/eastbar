package org.eastbar.alert2db.dao;

import java.util.List;

import org.eastbar.alert2db.entity.SiteAlert;


/**
 * Created by AndySJTU on 2015/6/4.
 */
@MyBatisRepository
public interface SiteAlertDao  {
	
	public void saveListAlarmHistory(List<SiteAlert> alertList);
}
