/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy.dao;

import org.eastbar.centers.strategy.entity.KeyWord;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
public interface KeyWordDao {

    List<KeyWord> getAll(Integer attr);

    List<KeyWord> getAllAddKeywords(Integer version);

    List<KeyWord> getAllEditKeywords(Integer version);

    List<KeyWord> getAllRemoveKeywords(Integer version);

    List<String> getMonitorCodesByVersion(Integer params);

    String getKeyWordsByCondition(List<KeyWord> keyWords,String monitorCode);

}
