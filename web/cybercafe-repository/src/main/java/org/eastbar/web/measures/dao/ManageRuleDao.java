/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.measures.entity.ManageRule;

/**
 * @author cindy-jia
 * @date 2014年11月08
 * @time 下午2:47
 * @description :
 */
@MyBatisRepository
public interface ManageRuleDao {

    ManageRule get();
    void update(ManageRule manageRule);
}
