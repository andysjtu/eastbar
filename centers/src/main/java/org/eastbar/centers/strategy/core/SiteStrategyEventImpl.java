/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.core;

import org.eastbar.centers.strategy.SiteStrategyEvent;
import org.eastbar.centers.strategy.dao.*;
import org.eastbar.centers.strategy.entity.SiteInfo;
import org.eastbar.centers.strategy.service.*;
import org.eastbar.centers.strategy.util.Strategy;
import org.eastbar.centers.strategy.util.Times;
import org.eastbar.common.redis.SiteRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月29
 * @time 上午10:09
 * @description :
 */
@Component
public class SiteStrategyEventImpl implements SiteStrategyEvent {

    @Autowired
    private SiteRedisService siteRedisService;

    @Autowired
    private BannedProgService bannedProgService;

    @Autowired
    private BannedUrlService bannedUrlService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private SpecialCustomerService specialCustomerService;

    @Autowired
    private ManageRuleService manageRuleService;

    @Autowired
    private BannedProgDao bannedProgDao;

    @Autowired
    private BannedUrlDao bannedUrlDao;

    @Autowired
    private KeyWordDao keyWordDao;

    @Autowired
    private SpecialCustomerDao specialCustomerDao;

    @Autowired
    private SiteDao siteDao;

    @Override
    public String returnProgList(String siteCode,int verNum) throws Exception {
        Integer redisVersion=0;
        //updatedVersion是redis已更新的prog的最新版本号
        String updatedVersion=siteRedisService.returnUpdatedVersion(Strategy.BANNEDPROG);
        String bannedProgs="";
        int newNum=verNum+1;
        if(updatedVersion!=null && !"".equals(updatedVersion)){
            redisVersion=Integer.parseInt(updatedVersion);
        }
        //如果reids的版本号大于场所传过来的本版号，则从redis拉取list
        if(redisVersion>=newNum){
            bannedProgs=siteRedisService.returnProgVersionList(siteCode,newNum);
            while("".equals(bannedProgs) && newNum<redisVersion){
                newNum++;
                bannedProgs=siteRedisService.returnProgVersionList(siteCode,newNum);
            }

        }else{//否则从数据库拉取list，更新
            int dbnum= (int) manageRuleService.getAll().get(Strategy.BANNEDPROG);
            if(dbnum>=newNum){
                bannedProgs=bannedProgService.siteControl(siteCode,newNum);
                while("".equals(bannedProgs) && newNum<dbnum){
                    newNum++;
                    bannedProgs=bannedProgService.siteControl(siteCode,newNum);
                }
            }

        }
        return bannedProgs;
    }

    @Override
    public String returnUrlList(String siteCode,int verNum) throws Exception {
        Integer redisVersion=0;
        //updatedVersion是redis已更新的url的最新版本号
        String updatedVersion=siteRedisService.returnUpdatedVersion(Strategy.BANNEDURL);
        String bannedUrls="";
        int newNum=verNum+1;
        if(updatedVersion!=null && !"".equals(updatedVersion)){
            redisVersion=Integer.parseInt(updatedVersion);
        }
        //如果reids的版本号大于场所传过来的本版号，则从redis拉取list
        if(redisVersion>=newNum){
            bannedUrls=siteRedisService.returnUrlVersionList(siteCode, newNum);
            while("".equals(bannedUrls) && newNum<redisVersion){
                newNum++;
                bannedUrls=siteRedisService.returnUrlVersionList(siteCode,newNum);
            }

        }else{//否则从数据库拉取list，更新
            int dbnum= (int) manageRuleService.getAll().get(Strategy.BANNEDURL);
            if(dbnum>=newNum){
                bannedUrls= bannedUrlService.siteControl(siteCode, newNum);
                while("".equals(bannedUrls) && newNum<dbnum){
                    newNum++;
                    bannedUrls= bannedUrlService.siteControl(siteCode, newNum);
                }

            }
        }
        return bannedUrls;
    }

    @Override
    public String returnKeyWordList(String siteCode,int verNum) throws Exception {
        Integer redisVersion=0;
        //updatedVersion是redis已更新的keyword的最新版本号
        String updatedVersion=siteRedisService.returnUpdatedVersion(Strategy.KEYWORD);
        String keywords="";
        int newNum=verNum+1;
        if(updatedVersion!=null && !"".equals(updatedVersion)){
            redisVersion=Integer.parseInt(updatedVersion);
        }
        //如果reids的版本号大于场所传过来的本版号，则从redis拉取list
        if(redisVersion>=newNum){
            keywords=siteRedisService.returnKeyWordVersionList(siteCode, newNum);
            while("".equals(keywords) && newNum<redisVersion){
                newNum++;
                keywords=siteRedisService.returnKeyWordVersionList(siteCode, newNum);
            }

        }else{//否则从数据库拉取list，更新
            int dbnum= (int) manageRuleService.getAll().get(Strategy.KEYWORD);
            if(dbnum>=newNum){
                keywords=keyWordService.siteControl(siteCode,newNum);
                while("".equals(keywords) && newNum<dbnum){
                    newNum++;
                    keywords=keyWordService.siteControl(siteCode,newNum);
                }
            }
        }
        return keywords;
    }

    @Override
    public String returnSpecialCustomerList(String siteCode,int verNum) throws Exception {
        Integer redisVersion=0;
        //updatedVersion是redis已更新的specialCustomer的最新版本号
        String updatedVersion=siteRedisService.returnUpdatedVersion(Strategy.SPECIALCUSTOMER);
        String specialCustomers="";
        int newNum=verNum+1;
        if(updatedVersion!=null && !"".equals(updatedVersion)){
            redisVersion=Integer.parseInt(updatedVersion);
        }
        //如果reids的版本号大于场所传过来的本版号，则从redis拉取list
        if(redisVersion>=newNum){
            specialCustomers=siteRedisService.returnSpecialCustomerVersionList(siteCode, newNum);
            while("".equals(specialCustomers) && newNum<redisVersion){
                newNum++;
                specialCustomers=siteRedisService.returnSpecialCustomerVersionList(siteCode, newNum);
            }
        }else{//否则从数据库拉取list，更新
            int dbnum=(int)manageRuleService.getAll().get(Strategy.SPECIALCUSTOMER);
            if(dbnum>=newNum){
                specialCustomers=specialCustomerService.siteControl(siteCode,newNum);
                while("".equals(specialCustomers) && newNum<dbnum){
                    newNum++;
                    specialCustomers=specialCustomerService.siteControl(siteCode,newNum);
                }
            }
        }
        return specialCustomers;
    }


    @Override
    public int lastedVersion(String measure) throws Exception{
        String measures="bannedProg,bannedUrl,keyword,specialCustomer";
        int version=0;
        if(measures.contains(measure)){
            version=(int)manageRuleService.getAll().get(measure);
        }else{
            //version=-1 说明不存在该中策略
            version=-1;
        }
        return version;
    }

    @Override
    public Map lastedVersions() throws Exception {
        return manageRuleService.getAll();
    }

    @Override
    public int writeSiteProgVersion(String siteCode,int verNum) {

        int result=0;
        //根据场所提供的编码，去数据库判断是否存在id，有id则site存在，否则不存在
        SiteInfo siteInfo =siteDao.getByCode(siteCode);
        if(siteInfo !=null){//如果site存在
            String version=bannedProgDao.getAllProgs(verNum).get(0).getVersion();
            siteInfo.setProgVer(version);
            result=siteDao.updateSite(siteInfo);
        }else{
           saveSite(siteCode);
        }
        return result;
    }

    @Override
    public int writeSiteUrlVersion(String siteCode,int verNum) {
        int result=0;
        //根据场所提供的编码，去数据库判断是否存在id，有id则site存在，否则不存在
        SiteInfo siteInfo =siteDao.getByCode(siteCode);
        if(siteInfo !=null){//如果site存在
            String version=bannedUrlDao.getAll(verNum).get(0).getVersion();
            siteInfo.setUrlVer(version);
            result=siteDao.updateSite(siteInfo);
        }else{
           saveSite(siteCode);
        }
        return result;
    }

    @Override
    public int writeSiteSpecialVersion(String siteCode,int verNum) {
        int result=0;
        //根据场所提供的编码，去数据库判断是否存在id，有id则site存在，否则不存在
        SiteInfo siteInfo =siteDao.getByCode(siteCode);
        if(siteInfo !=null){//如果site存在
            String version=specialCustomerDao.getAll(verNum).get(0).getVersion();
            siteInfo.setSpecialVer(version);
            result=siteDao.updateSite(siteInfo);
        }else{
           saveSite(siteCode);
        }
        return result;
    }

    @Override
    public int writeSiteKeywordVersion(String siteCode,int verNum) {
        int result=0;
        //根据场所提供的编码，去数据库判断是否存在id，有id则site存在，否则不存在
        SiteInfo siteInfo =siteDao.getByCode(siteCode);
        if(siteInfo !=null){//如果site存在
            String version=keyWordDao.getAll(verNum).get(0).getVersion();
            siteInfo.setKeywordVer(version);
            result=siteDao.updateSite(siteInfo);
        }else{
          saveSite(siteCode);
        }
        return result;
    }

    @Override
    public int refreshList(List<String> list) {
        return 0;
    }

    /**
     * 初始化site
     * @param siteCode
     * @return
     */
    private int saveSite(String siteCode){
        SiteInfo siteInfo =new SiteInfo();
        siteInfo.setSiteCode(siteCode);
        siteInfo.setMonitorCode(siteCode);
        siteInfo.setName(siteCode);
        siteInfo.setRegStatus("-1");
        siteInfo.setAddress(siteCode);
        siteInfo.setZip(siteCode);
        siteInfo.setLegalRepresent(siteCode);
        siteInfo.setPrincipal(siteCode);
        siteInfo.setPrincipalTel(siteCode);
        siteInfo.setAdminstrator(siteCode);
        siteInfo.setAdminTel(siteCode);
        siteInfo.setTerminalNum(siteCode);
        siteInfo.setCreateTime(Times.now());
        siteInfo.setCreator(siteCode);
        siteInfo.setProgVer(Strategy.INITIAL_VERSION);
        siteInfo.setHourVer(Strategy.INITIAL_VERSION);
        siteInfo.setUrlVer(Strategy.INITIAL_VERSION);
        siteInfo.setSpecialVer(Strategy.INITIAL_VERSION);
        siteInfo.setKeywordVer(Strategy.INITIAL_VERSION);
       int result=siteDao.save(siteInfo);
       return result;
    }
}
