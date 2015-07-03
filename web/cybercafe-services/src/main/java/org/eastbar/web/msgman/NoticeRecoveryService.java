/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman;

import org.eastbar.web.PageInfo;
import org.eastbar.web.msgman.biz.NoticeRecoveryBO;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午4:29
 * @description :
 */
public interface NoticeRecoveryService {

    NoticeRecoveryBO get(Integer id);
    PageInfo getAll(NoticeRecoveryBO noticeRecoveryBO);
}
