/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao.impl;

import org.eastbar.center.Po2Json;
import org.eastbar.center.strategy.dao.BannedUrlDao;
import org.eastbar.center.strategy.entity.BannedUrl;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月07
 * @time 上午10:28
 * @description :
 */
@Repository
public class BannedUrlDaoImpl implements BannedUrlDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<BannedUrl> getAll(Integer parmas) {
        List<BannedUrl> bannedUrls=sqlSession.selectList("getAllUrl",parmas);
        return bannedUrls;
    }

    @Override
    public List<BannedUrl> getAllAddUrls(Integer version) {
        List<BannedUrl> bannedUrls=sqlSession.selectList("getAllAddUrls",version);
        return bannedUrls;
    }

    @Override
    public List<BannedUrl> getAllEditUrls(Integer version) {
        List<BannedUrl> bannedUrls=sqlSession.selectList("getAllEditUrls",version);
        return bannedUrls;
    }

    @Override
    public List<BannedUrl> getAllRemoveUrls(Integer version) {
        List<BannedUrl> bannedUrls=sqlSession.selectList("getAllRemoveUrls",version);
        return bannedUrls;
    }

    @Override
    public List<String> getMonitorCodesByVersion(Integer params) {
        List<String> monitorCodes=sqlSession.selectList("getUrlMonitorCodesByVersion", params);
        return monitorCodes;
    }

    @Override
    public String getUrlssByCondition(List<BannedUrl> bannedUrls, String monitorCode) {
        List<BannedUrl> bannedUrlList=new ArrayList<>();

        for(int i=0;i<bannedUrls.size();i++){
            BannedUrl bannedUrl=bannedUrls.get(i);
            String monitorCode_1=bannedUrl.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                bannedUrlList.add(bannedUrl);
            }
        }
        if(bannedUrlList.size()>0){
            return Po2Json.toJson(bannedUrlList);
        }
        else{
            return "";
        }

    }

}
