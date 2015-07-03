/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.dao;


import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.measures.entity.BannedUrl;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
@MyBatisRepository
public interface BannedUrlDao {

    BannedUrl getBannedUrl(Integer id);
    List<BannedUrl> getAllBannedUrl(Map<String, Object> attr);
    void save(BannedUrl bannerUrl);
    void delete(Integer id);
    void update(BannedUrl bannerUrl);
    List<BannedUrl> getBannedUrlsByUrlType(Map<String, Object> attr);

}
