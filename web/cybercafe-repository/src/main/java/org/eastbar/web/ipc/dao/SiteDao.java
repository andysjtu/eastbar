/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.Site;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午4:47
 * @description :
 */
@MyBatisRepository
public interface SiteDao {
    Site get(String siteCode);
    List<Site> byMonitorCode(Map<String, Object> attr);
    List<Site> findListbyMonitorCode(String monitorCode);
    List<Site> getListLikeMonitorCode(String monitorCode);
    void save(Site site);
    void delete(String siteCode);
    void update(Site site);

	List<Site> getAll();
	List getSiteOnLine(Map<String, Object> attr);
}
