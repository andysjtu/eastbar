/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao;

import org.eastbar.center.strategy.entity.ManageRule;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月14
 * @time 上午11:52
 * @description :
 */
public interface ManageRuleDao {

    List<ManageRule> getAll();
    ManageRule get();
    void update(ManageRule manageRule);

}
