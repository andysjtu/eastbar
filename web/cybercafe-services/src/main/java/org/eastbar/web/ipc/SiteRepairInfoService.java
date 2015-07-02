package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.SiteRepairInfoBO;

/**
 * Created by yangyd on 15-5-21.
 */
public interface SiteRepairInfoService {
	PageInfo getAll(SiteRepairInfoBO siteRepairInfoBO);
}
