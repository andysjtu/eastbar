/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.dao;


import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.measures.entity.BannedProg;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
@MyBatisRepository
public interface BannedProgDao {

    BannedProg getBannedProg(Integer id);
    List<BannedProg> getAllBannedProg(Map<String, Object> attr);
    void save(BannedProg bannedProg);
    void delete(Integer id);
    void update(BannedProg bannedProg);


}
