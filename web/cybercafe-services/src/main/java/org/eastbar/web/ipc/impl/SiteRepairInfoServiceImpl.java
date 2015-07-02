package org.eastbar.web.ipc.impl;

import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.SiteRepairInfoService;
import org.eastbar.web.ipc.biz.SiteRepairInfoBO;
import org.eastbar.web.ipc.dao.SiteRepairInfoDao;
import org.eastbar.web.ipc.entity.SiteRepairInfo;
import org.eastbar.web.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by yangyd on 15-5-21.
 */
@Service
@Transactional
public class SiteRepairInfoServiceImpl implements SiteRepairInfoService {

	@Autowired
	private SiteRepairInfoDao siteRepairInfoDao;

	@Override
	public PageInfo getAll(SiteRepairInfoBO siteRepairInfoBO) {
		try{
			PageHelper.startPage(siteRepairInfoBO.getPage(), siteRepairInfoBO.getRows());
			Map<String,Object> re= Bean2Map.trans(siteRepairInfoBO);
			List<SiteRepairInfo> list= siteRepairInfoDao.getAll(re);
			return PageInfo.clone(list);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
