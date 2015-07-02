/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures;


import org.eastbar.web.PageInfo;
import org.eastbar.web.measures.biz.BannedProgBO;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:22
 * @description :
 */
public interface BannedProgService {

    BannedProgBO getBannedProg(Integer id);
    PageInfo getAllBannedProg(BannedProgBO bannedProgBO);
    Boolean save(BannedProgBO bannedProgBO);
    Boolean delete(Integer id);
    Boolean update(BannedProgBO bannedProgBO);
    Boolean deleteMany(Integer[] ids);
    int releaseMany(Integer[] ids);
}
