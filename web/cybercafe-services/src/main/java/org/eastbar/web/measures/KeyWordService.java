/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures;

import org.eastbar.web.PageInfo;
import org.eastbar.web.measures.biz.KeyWordBO;


/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:22
 * @description :
 */
public interface KeyWordService {

    KeyWordBO getKeyWord(Integer id);
    PageInfo getAllKeyWord(KeyWordBO keyWordBO);
    Boolean save(KeyWordBO keyWordBO);
    Boolean delete(Integer id);
    Boolean update(KeyWordBO keyWordBO);
    Boolean deleteMany(Integer[] ids);
    int releaseMany(Integer[] ids);
}
