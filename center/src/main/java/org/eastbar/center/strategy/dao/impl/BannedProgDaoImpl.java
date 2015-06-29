/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao.impl;

import org.eastbar.center.strategy.entity.BannedProg;
import org.eastbar.center.Po2Json;
import org.eastbar.center.strategy.dao.BannedProgDao;
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
public class BannedProgDaoImpl implements BannedProgDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<BannedProg> getAllProgs(Integer parmas) {
        List<BannedProg> bannedProgs=sqlSession.selectList("getAllProgs",parmas);
        return bannedProgs;
    }

    @Override
    public String getProgsByCondition(List<BannedProg> bannedProgList,String monitorCode) {
        List<BannedProg> bannedProgs=new ArrayList<>();

        for(int i=0;i<bannedProgList.size();i++){
            BannedProg bannedProg=bannedProgList.get(i);
            String monitorCode_1=bannedProg.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                bannedProgs.add(bannedProg);
            }
        }
        if(bannedProgs.size()>0){
            return Po2Json.toJson(bannedProgs);
        }else{
            return "";
        }

    }

    @Override
    public List<String> getMonitorCodesByVersion(Integer params) {
        List<String> monitorCodes=sqlSession.selectList("getProgMonitorCodesByVersion", params);
        return monitorCodes;
    }

    @Override
    public List<BannedProg> getAllAddProgs(Integer version) {
        List<BannedProg> bannedProgs=sqlSession.selectList("getAllAddProgs",version);
        return bannedProgs;
    }

    @Override
    public List<BannedProg> getAllEditProgs(Integer version) {
        List<BannedProg> bannedProgs=sqlSession.selectList("getAllEditProgs",version);
        return bannedProgs;
    }

    @Override
    public List<BannedProg> getAllRemoveProgs(Integer version) {
        List<BannedProg> bannedProgs=sqlSession.selectList("getAllRemoveProgs",version);
        return bannedProgs;
    }

    @Override
    public void update(BannedProg bannedProg) {
        sqlSession.update("updateProg",bannedProg);
    }

    @Override
    public BannedProg get(Integer id) {
        return sqlSession.selectOne("getProg",id);
    }
}
