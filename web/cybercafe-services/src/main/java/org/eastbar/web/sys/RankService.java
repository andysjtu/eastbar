/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys;

import org.eastbar.web.PageInfo;
import org.eastbar.web.sys.biz.RankBO;

/**
 * @author cindy-jia
 * @date 2014年10月24
 * @time 下午8:05
 * @description :
 */
public interface RankService {

    RankBO get(Integer id);
    Boolean update(RankBO rankBO);
    PageInfo getAllRank(RankBO rankBO);
}
