/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.biz.TerminalBO;

import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月29
 * @time 下午2:14
 * @description :
 */
public interface TerminalLogService {
    List<TerminalBO> getOnlineTerminal(String siteCode);
    TerminalBO getSiteTerminalInfo(String siteCode);
    PageInfo getOnlineTerminalByBO(TerminalBO terminalBO);
}
