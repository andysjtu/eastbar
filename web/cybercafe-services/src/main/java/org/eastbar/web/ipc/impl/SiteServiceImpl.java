/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.common.redis.WebRedisService;
import org.eastbar.common.rmi.RmiService;
import org.eastbar.web.Bean2Map;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.ipc.SiteLiveDataService;
import org.eastbar.web.ipc.SiteService;
import org.eastbar.web.ipc.TerminalLogService;
import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.biz.TerminalBO;
import org.eastbar.web.ipc.dao.SiteDao;
import org.eastbar.web.ipc.entity.Site;
import org.eastbar.web.ipc.entity.SiteLiveData;
import org.eastbar.web.measures.ManageRuleService;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.AlarmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月21
 * @time 下午3:41
 * @description :
 */

@Service
@Transactional
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;
    @Autowired
    private SiteLiveDataService siteLiveDataService;
    @Autowired
    private RmiService rmiService;
    @Autowired
    private WebRedisService redisBasicService;
    @Autowired
    private ManageRuleService manageRuleService;
    @Autowired
    private AlarmHistoryService alarmHistoryService;

    @Autowired
    private TerminalLogService terminalLogService;

    @Override
    public SiteBO get(String siteCode) {
        SiteBO sbo = new SiteBO();
        try {
            BeanUtils.copyProperties(sbo, siteDao.get(siteCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sbo;
    }

    @Override
    public PageInfo byMonitorCode(SiteBO siteBO) {
        try {
            PageHelper.startPage(siteBO.getPage(), siteBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(siteBO);
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            if("-1".equals(siteBO.getMonitorCode())){
                List<String> codes=ShiroCustomUtils.getMonitors();
                if(codes.size()>0){
                    re.put("monitorCode",codes.get(0));
                }else{
                    re.remove("monitorCode");
                }

            }
            List<Site> list = siteDao.byMonitorCode(re);

            List<SiteBO> sbl = new ArrayList<>();
            try{
                //TODO 连接REDIS获取实时统计数据
                for(Site m:list){
                    SiteBO sb=new SiteBO();
                    BeanUtils.copyProperties(sb,m);
                    Map<String,String> site=redisBasicService.getSiteHash(m.getSiteCode());
                    BeanUtils.populate(sb,site);
                    Long counts=alarmHistoryService.getCountByCode(sb.getSiteCode());
                    sb.setTotalAlarm(counts);
                    TerminalBO tb = terminalLogService.getSiteTerminalInfo(sb.getSiteCode());
                    if(tb!=null){
                        if(tb.getSiteTerminalTotalNum()!=null && tb.getSiteTerminalUnknowNum()!=null){
                            int usingNum=tb.getSiteTerminalTotalNum();
                            int unKnownNum=tb.getSiteTerminalUnknowNum();
                            if(usingNum!=0 && unKnownNum!=0){
                                double rate=(usingNum+0.0)/(unKnownNum+usingNum+0.0);
                                sb.setInstallationRate(String.format("%.2f", rate*100)+"%");
                            }else{
                                sb.setInstallationRate("0.0%");
                            }
                        }
                    }
                    sbl.add(sb);
                }
            }catch(Exception e){
                Map<String,SiteLiveData> liveLatest = siteLiveDataService.getAllLatest();
                for(Site s:list){
                    SiteBO sb = new SiteBO();
                    BeanUtils.copyProperties(sb, s);
                    if(liveLatest.containsKey(s.getSiteCode())){
                        BeanUtils.copyProperties(sb,liveLatest.get(s.getSiteCode()));
                    }
                    sbl.add(sb);
                }
            }
            return PageInfo.clone(list, sbl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional
    public Boolean save(SiteBO siteBO) {
        Site site=new Site();
        try {
            BeanUtils.copyProperties(site, siteBO);
            site.setCreateTime(Times.now());
            site.setCreator(ShiroCustomUtils.getCurrentUserName());
            site.setHourVer("1.0.0");
            site.setProgVer("1.0.0");
            site.setSpecialVer("1.0.0");
            site.setUrlVer("1.0.0");
            site.setKeywordVer("1.0.0");
            siteDao.save(site);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean delete(String siteCode) {
        try {
            siteDao.delete(siteCode);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean update(SiteBO siteBO) {
        Site site=new Site();
        try {
            BeanUtils.copyProperties(site, siteBO);
            site.setUpdateTime(Times.now());
            site.setUpdator(ShiroCustomUtils.getCurrentUserName());
            siteDao.update(site);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteMany(String[] siteCodes) {
        try {
            for(int i=0;i<siteCodes.length;i++){
                //Integer id=Integer.parseInt(siteCodes[i]+"");
                siteDao.delete(siteCodes[i]);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SiteBO> findListbyMonitorCode(String monitorCode) {
        List<SiteBO> siteBOs=new ArrayList<>();
        try{
            List<Site> siteList=siteDao.findListbyMonitorCode(monitorCode);
            for(Site s:siteList){
                SiteBO siteBO=new SiteBO();
                BeanUtils.copyProperties(siteBO,s);
                siteBOs.add(siteBO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return siteBOs;
    }

	@Override
	public List<Site> getAll() {
		return siteDao.getAll();
	}

	@Override
	public PageInfo getSiteOnLine(SiteBO siteBO) {
		try {
			PageHelper.startPage(siteBO.getPage(), siteBO.getRows());

			Map<String,Object> re = Bean2Map.trans(siteBO);
			List list = siteDao.getSiteOnLine(re);
			return PageInfo.clone(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
