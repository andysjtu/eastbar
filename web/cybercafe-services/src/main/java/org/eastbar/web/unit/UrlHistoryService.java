/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.UrlHistoryBO;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:13
 * @description :
 */
public interface UrlHistoryService {

    UrlHistoryBO getUrlHistory(Integer id);
    PageInfo getAllUrlHistory(UrlHistoryBO urlHistoryBO);
    Boolean save(UrlHistoryBO urlHistoryBO);
    Boolean update(UrlHistoryBO urlHistoryBO);
    Boolean delete(Integer id);
}
