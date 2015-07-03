/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.SitePunishBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年11月03
 * @time 下午3:07
 * @description :
 */
public interface SitePunishService {

    SitePunishBO getSitePunish(Integer id);
    PageInfo getAllSitePunish(SitePunishBO sitePunishBO);
    Boolean save(SitePunishBO sitePunishBO);
    Boolean delete(Integer id);
    Boolean update(SitePunishBO sitePunishBO);

	List getPunishReasonResult(SitePunishBO sitePunishBO);
	List getPunishTypeResult(SitePunishBO sitePunishBO);
}
