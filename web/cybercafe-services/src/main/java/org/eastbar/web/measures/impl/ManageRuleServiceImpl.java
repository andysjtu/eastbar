/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.impl;

import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.measures.dao.ManageRuleDao;
import org.eastbar.web.measures.entity.ManageRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cindy-jia
 * @date 2014年11月08
 * @time 下午5:33
 * @description :
 */
@Service
public class ManageRuleServiceImpl implements ManageRuleService {


    @Autowired
    private ManageRuleDao manageRuleDao;


    @Override
    public ManageRule get() {
        return manageRuleDao.get();
    }
}
