/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.TerminalLog;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月29
 * @time 下午1:51
 * @description :
 */
@MyBatisRepository
public interface TerminalLogDao {
    List<TerminalLog> getOnlineTerminal(String siteCode);
    List<TerminalLog> getOnlineTerminalByBO(Map<String, Object> attr);
}
