/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao;

import org.eastbar.center.strategy.entity.SiteInfo;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午4:30
 * @description :
 */
public interface SiteDao {

    int updateSite(SiteInfo siteInfo);

    int save(SiteInfo siteInfo);

    SiteInfo getByCode(String siteCode);
}
