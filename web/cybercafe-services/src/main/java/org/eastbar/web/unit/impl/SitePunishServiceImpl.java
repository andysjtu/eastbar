/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.SitePunishService;
import org.eastbar.web.unit.biz.SitePunishBO;
import org.eastbar.web.unit.dao.SitePunishDao;
import org.eastbar.web.unit.entity.SitePunish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年11月03
 * @time 下午3:09
 * @description :
 */
@Service
@Transactional
public class SitePunishServiceImpl implements SitePunishService {
    @Autowired
    private SitePunishDao sitePunishDao;

    @Override
    public SitePunishBO getSitePunish(Integer id) {
        try {
            SitePunishBO sitePunishBO=new SitePunishBO();
            BeanUtils.copyProperties(sitePunishBO, sitePunishDao.getSitePunish(id));
            return sitePunishBO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo getAllSitePunish(SitePunishBO sitePunishBO) {
        try {
            PageHelper.startPage(sitePunishBO.getPage(), sitePunishBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(sitePunishBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));//域
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            List<SitePunish> list = sitePunishDao.getAllSitePunish(re);
            return PageInfo.clone(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean save(SitePunishBO sitePunishBO) {
        try {
            SitePunish sitePunish=new SitePunish();
            BeanUtils.copyProperties(sitePunish, sitePunishBO);
            sitePunishDao.save(sitePunish);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(SitePunishBO sitePunishBO) {
        try {
            SitePunish sitePunish=new SitePunish();
            BeanUtils.copyProperties(sitePunish, sitePunishBO);
            sitePunishDao.update(sitePunish);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            sitePunishDao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public List getPunishReasonResult(SitePunishBO sitePunishBO) {
		try {
			Map<String,Object> re = Bean2Map.trans(sitePunishBO);
			List<SitePunish> list = sitePunishDao.getPunishReasonResult(re);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List getPunishTypeResult(SitePunishBO sitePunishBO) {
		try {
			Map<String,Object> re = Bean2Map.trans(sitePunishBO);
			List<SitePunish> list = sitePunishDao.getPunishTypeResult(re);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
