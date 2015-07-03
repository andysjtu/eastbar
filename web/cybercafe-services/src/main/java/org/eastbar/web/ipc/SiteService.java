/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.entity.Site;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:41
 * @description :
 */
public interface SiteService {

    SiteBO get(String siteCode);
    PageInfo byMonitorCode(SiteBO siteBO);
    Boolean save(SiteBO siteBO);
    Boolean delete(String siteCode);
    Boolean update(SiteBO siteBO);
    Boolean deleteMany(String[] siteCodes);

    List<SiteBO> findListbyMonitorCode(String monitorCode);

	List<Site> getAll();
	PageInfo getSiteOnLine(SiteBO siteBO);
}
