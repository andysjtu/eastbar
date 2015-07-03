/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.dao;


import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.measures.entity.KeyWord;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
@MyBatisRepository
public interface KeyWordDao {

    KeyWord getKeyWord(Integer id);
    List<KeyWord> getAllKeyWord(Map<String, Object> attr);
    void save(KeyWord bannerUrl);
    void delete(Integer id);
    void update(KeyWord keyWord);
}
