/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.sys.RankService;
import org.eastbar.web.sys.biz.RankBO;
import org.eastbar.web.sys.dao.RankDao;
import org.eastbar.web.sys.entity.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月24
 * @time 下午8:08
 * @description :
 */
@Service
@Transactional
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public RankBO get(Integer id) {
        try {
            RankBO rankBO = new RankBO();
            BeanUtils.copyProperties(rankBO, rankDao.get(id));
            return rankBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(RankBO rankBO) {
        Rank rank = new Rank();
        try {
            BeanUtils.copyProperties(rank,rankBO);
            rank.setUpdator(ShiroCustomUtils.getCurrentUserName());
            rank.setUpdateTime(Times.now());
            rankDao.update(rank);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PageInfo getAllRank(RankBO rankBO) {
        try {
            PageHelper.startPage(rankBO.getPage(), rankBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(rankBO);
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<Rank> list = rankDao.getAllRank(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
