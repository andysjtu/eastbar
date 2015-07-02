/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman;

import org.eastbar.web.PageInfo;
import org.eastbar.web.msgman.biz.NoticeBO;

/**
 * @author cindy-jia
 * @date 2014年10月23
 * @time 下午4:56
 * @description :
 */
public interface NoticeService {

    Boolean save(NoticeBO noticeBO);
    Boolean delete(Integer id);
    Boolean update(NoticeBO noticeBO);
    NoticeBO get(Integer id);
    PageInfo getAllPublic(NoticeBO noticeBO);
    PageInfo getAllReceive(NoticeBO noticeBO);
    Boolean deleteMany(String[] ids);
}
