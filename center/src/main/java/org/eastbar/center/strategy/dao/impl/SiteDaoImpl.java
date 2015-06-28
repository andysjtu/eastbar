/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao.impl;

import org.eastbar.center.strategy.dao.SiteDao;
import org.eastbar.center.strategy.entity.SiteInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午4:31
 * @description :
 */
@Repository
public class SiteDaoImpl implements SiteDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int updateSite(SiteInfo siteInfo) {
        int result=sqlSession.update("update", siteInfo);
        return result;
    }
    @Override
    public int save(SiteInfo siteInfo){
        int result=sqlSession.insert("save", siteInfo);
        return result;
    }

    @Override
    public SiteInfo getByCode(String siteCode) {
        SiteInfo siteInfo =sqlSession.selectOne("getByCode", siteCode);
        return siteInfo;
    }
}
