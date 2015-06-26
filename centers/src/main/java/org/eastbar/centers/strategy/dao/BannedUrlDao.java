/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.dao;


import org.eastbar.centers.strategy.entity.BannedUrl;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
public interface BannedUrlDao {

    List<BannedUrl> getAll(Integer attr);

    List<BannedUrl> getAllAddUrls(Integer version);

    List<BannedUrl> getAllEditUrls(Integer version);

    List<BannedUrl> getAllRemoveUrls(Integer version);


    List<String> getMonitorCodesByVersion(Integer params);

    String getUrlssByCondition(List<BannedUrl> bannedUrls,String monitorCode);


}
