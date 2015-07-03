/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.SiteLiveDataBO;
import org.eastbar.web.ipc.entity.SiteLiveData;

import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:45
 * @description :
 */
public interface SiteLiveDataService {

    SiteLiveData get(String siteCode);
    Map<String,SiteLiveData> getAllLatest();
    SiteLiveData getLiveCache(String siteCode);
    PageInfo getAll(SiteLiveDataBO siteLiveDataBO);
}
