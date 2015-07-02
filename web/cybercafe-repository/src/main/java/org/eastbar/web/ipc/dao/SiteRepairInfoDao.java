package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.SiteRepairInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyd on 15-5-21.
 */
@MyBatisRepository
public interface SiteRepairInfoDao {
	List<SiteRepairInfo> getAll(Map<String, Object> attr);
}
