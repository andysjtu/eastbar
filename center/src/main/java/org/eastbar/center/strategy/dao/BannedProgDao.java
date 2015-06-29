/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.dao;


import org.eastbar.center.strategy.entity.BannedProg;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月07
 * @time 上午10:27
 * @description :
 */
public interface BannedProgDao {

    //根据版本号获取所有该版本的策略信息
    List<BannedProg> getAllProgs(Integer version);

    List<BannedProg> getAllAddProgs(Integer version);

    List<BannedProg> getAllEditProgs(Integer version);

    List<BannedProg> getAllRemoveProgs(Integer version);

    //根据版本号获取所有的监管中心编码
    List<String> getMonitorCodesByVersion(Integer version);
    //根据条件
    String getProgsByCondition(List<BannedProg> bannedProgs,String monitorCode);

    void update(BannedProg bannedProg);

    BannedProg get(Integer id);

}
