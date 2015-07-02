/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;

import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.ProgHistoryBO;

/**
 * @author cindy-jia
 * @date 2014年10月17
 * @time 上午10:12
 * @description :
 */
public interface ProgHistoryService {

    ProgHistoryBO getProgHistory(Integer id);
    PageInfo getAllProgHistory(ProgHistoryBO progHistoryBO);
    Boolean save(ProgHistoryBO progHistoryBO);
    Boolean update(ProgHistoryBO ProgHistoryBO);
    Boolean delete(Integer id);
}
