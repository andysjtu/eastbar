/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.service.impl;

import org.eastbar.centers.strategy.dao.ManageRuleDao;
import org.eastbar.centers.strategy.entity.ManageRule;
import org.eastbar.centers.strategy.service.ManageRuleService;
import org.eastbar.common.redis.util.Strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月14
 * @time 下午12:03
 * @description :
 */
@Service
public class ManageRuleServiceImpl implements ManageRuleService {

    @Autowired
    private ManageRuleDao manageRuleDao;
    @Override
    public Map<String,Object> getAll() {
        //从数据库获取策略库总表
        List<ManageRule> manageRules=manageRuleDao.getAll();
        ManageRule manageRule=manageRules.get(0);
        //将数据库策略库总表的信息对应的放到map里面
        Map<String,Object> map=new HashMap<>();
        map.put(Strategy.BANNEDPROG,manageRule.getProgVerNum());
        map.put(Strategy.BANNEDURL,manageRule.getUrlVerNum());
        map.put(Strategy.KEYWORD,manageRule.getKeywordVerNum());
        map.put(Strategy.SPECIALCUSTOMER,manageRule.getSpecialVerNum());
        return map;
    }
}
