/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao.impl;

import org.eastbar.center.Po2Json;
import org.eastbar.center.strategy.dao.KeyWordDao;
import org.eastbar.center.strategy.entity.KeyWord;
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
public class KeyWordDaoImpl implements KeyWordDao {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<KeyWord> getAll(Integer parmas) {
        List<KeyWord> keyWords=sqlSession.selectList("getAllKeyWord",parmas);
        return keyWords;
    }

    @Override
    public List<String> getMonitorCodesByVersion(Integer params) {
        List<String> monitorCodes=sqlSession.selectList("getKeyWordMonitorCodesByVersion", params);
        return monitorCodes;
    }

    @Override
    public List<KeyWord> getAllAddKeywords(Integer version) {
        List<KeyWord> keyWords=sqlSession.selectList("getAllAddKeywords",version);
        return keyWords;
    }

    @Override
    public List<KeyWord> getAllEditKeywords(Integer version) {
        List<KeyWord> keyWords=sqlSession.selectList("getAllEditKeywords",version);
        return keyWords;
    }

    @Override
    public List<KeyWord> getAllRemoveKeywords(Integer version) {
        List<KeyWord> keyWords=sqlSession.selectList("getAllRemoveKeywords",version);
        return keyWords;
    }

    @Override
    public String getKeyWordsByCondition(List<KeyWord> keyWords, String monitorCode) {
        List<KeyWord> keyWordList=new ArrayList<>();

        for(int i=0;i<keyWords.size();i++){
            KeyWord keyWord=keyWords.get(i);
            String monitorCode_1=keyWord.getMonitorCode();
            if(monitorCode.equals(monitorCode_1)){
                keyWordList.add(keyWord);
            }
        }
        if(keyWordList.size()>0){
            return Po2Json.toJson(keyWordList);
        }else{
            return "";
        }

    }


}
