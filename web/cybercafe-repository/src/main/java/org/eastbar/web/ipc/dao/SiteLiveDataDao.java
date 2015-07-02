/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.SiteLiveData;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午4:48
 * @description :
 */
@MyBatisRepository
public interface SiteLiveDataDao {
    SiteLiveData get(String siteCode);
    List<SiteLiveData> getAllLatest();
    List<SiteLiveData> getAll(Map<String, Object> re);
}
