/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.dao;


import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.unit.entity.SitePunish;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
@MyBatisRepository
public interface SitePunishDao {

    SitePunish getSitePunish(Integer id);
    List<SitePunish> getAllSitePunish(Map<String, Object> attr);
    void save(SitePunish sitePunish);
    void delete(Integer id);
    void update(SitePunish sitePunish);

	List getPunishReasonResult(Map<String, Object> attr);
	List getPunishTypeResult(Map<String, Object> attr);
}
