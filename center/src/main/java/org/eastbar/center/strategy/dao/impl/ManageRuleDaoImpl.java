/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao.impl;

import org.eastbar.center.strategy.dao.ManageRuleDao;
import org.eastbar.center.strategy.entity.ManageRule;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月14
 * @time 上午11:54
 * @description :
 */
@Repository
public class ManageRuleDaoImpl implements ManageRuleDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ManageRule> getAll() {
        return sqlSession.selectList("getAll");
    }
}
