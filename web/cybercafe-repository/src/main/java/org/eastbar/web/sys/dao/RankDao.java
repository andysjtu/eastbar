/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.sys.entity.Rank;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月24
 * @time 下午7:51
 * @description :
 */
@MyBatisRepository
public interface RankDao {

    void update(Rank rank);
    Rank get(Integer id);
    List<Rank> getAllRank(Map<String, Object> attr);
}
