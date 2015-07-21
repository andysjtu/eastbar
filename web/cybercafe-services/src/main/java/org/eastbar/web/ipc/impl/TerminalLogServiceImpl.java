/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.common.redis.CenterRedisService;
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
import org.eastbar.web.ipc.dao.TerminalLogDao;
import org.eastbar.web.ipc.entity.SiteLiveData;
import org.eastbar.web.ipc.entity.TerminalLog;
import org.eastbar.web.pagehelper.PageHelper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月29
 * @time 下午2:16
 * @description :
 */
@Service
@Transactional
public class TerminalLogServiceImpl implements TerminalLogService {

    @Autowired
    private TerminalLogDao terminalLogDao;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SiteLiveDataService siteLiveDataService;
    @Autowired
    private RmiService rmiService;
    @Autowired
    private WebRedisService redisBasicService;

    @Override
    public List<TerminalBO> getOnlineTerminal(String siteCode) {
        List<TerminalBO> tbs = new ArrayList<>();
        try{
            Set<String> ips=redisBasicService.getIpSet(siteCode);
            Iterator iterator=ips.iterator();
            while(iterator.hasNext()){
                Map<String,String> terminalMap=redisBasicService.getTerminalHash((String)iterator.next(),siteCode);
                TerminalBO terminalBO=new TerminalBO();
                BeanUtils.populate(terminalBO,terminalMap);
                tbs.add(terminalBO);
            }
        }catch(Exception e){
            List<TerminalLog> tls = terminalLogDao.getOnlineTerminal(siteCode);
            for(TerminalLog tl:tls){
                TerminalBO tb = new TerminalBO();
                try {
                    BeanUtils.copyProperties(tb,tl);
                } catch (IllegalAccessException|InvocationTargetException e1) {
                    e1.printStackTrace();
                }
                tbs.add(tb);
            }
        }
        return tbs;

    }

    @Override
    public PageInfo getOnlineTerminalByBO(TerminalBO terminalBO) {
        try {
            PageHelper.startPage(terminalBO.getPage(), terminalBO.getRows());

            Long t1 = System.currentTimeMillis();
            Map<String,Object> re = Bean2Map.trans(terminalBO);
            //获取当前用户的域
            List<String> monitorCodes= ShiroCustomUtils.getMonitors();
            if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                re.put("monitor",monitorCodes.get(0));//为了与查询条件monitorCode分开，这里用户所属域用monitor表示
            }
            System.out.println(System.currentTimeMillis() - t1 + " : " + re);
            if("-1".equals(terminalBO.getSiteCode())){//从左侧栏进入查询在线用户时，siteCode=-1
                re.remove("siteCode");
            }
            List<TerminalLog> list =  terminalLogDao.getOnlineTerminalByBO(re);
            PageInfo pr= PageInfo.clone(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TerminalBO getSiteTerminalInfo(String siteCode) {
        TerminalBO tb = new TerminalBO();
        tb.setSiteCode(siteCode);

        Integer[] state = new Integer[]{0,0,0,0};
        String isActive="";
        try{
            Map<String,String> site=redisBasicService.getSiteHash(siteCode);
            if(site.size()>0){
                String states=site.get("runStatus");
                states=states.substring(1,states.length()-1);
                String[] stateMap=states.split(",");
                isActive=site.get("isActive");
                //Map<String,String> stateMap = rmiService.operationsCount(siteCode);
                //freeHost,useHost,locking,close
                if(stateMap.length==4){
                    state[0] = Integer.parseInt(stateMap[1]);//空闲
                    state[1] = Integer.parseInt(stateMap[0]);//使用
                    state[2] = Integer.parseInt(stateMap[3]);//未知
                    state[3] = Integer.parseInt(stateMap[2]);//关机
                    //TODO 连接REDIS获取数的终端数
                    int num=state[1];
                    tb.setSiteTerminalTotalNum(num);
                    tb.setSiteTerminalUnknowNum(state[2]);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            tb.setSiteTerminalTotalNum(0);
            tb.setSiteTerminalUnknowNum(0);
        }

        tb.setSiteStatus(state);
        SiteBO sb = siteService.get(siteCode);
        tb.setSiteName(sb.getName());
        if(isActive!=null  && !"".equals(isActive)){
            tb.setSiteRunStatus(isActive);
        }else{
            tb.setSiteRunStatus("false");
        }

        SiteLiveData sld = siteLiveDataService.getLiveCache(siteCode);
        if(sld != null){
            tb.setLastUpdateTime(sld.getLastUpdateTime());
        }else{
            tb.setLastUpdateTime(Times.now());
        }
        return tb;
    }
}
