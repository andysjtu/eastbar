/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account;

import org.eastbar.web.PageInfo;
import org.eastbar.web.account.biz.RoleBO;
import org.eastbar.web.account.entity.Role;
import org.eastbar.web.ipc.entity.Monitor;

import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年09月28
 * @time 上午9:56
 * @description :
 */
public interface RoleService {
    RoleBO get(Integer id);
    RoleBO getRoleMonitor(Integer id);
    Boolean delete(Integer id);
    PageInfo getAllRole(RoleBO roleBO);
    List<Role> getRoles();
    Boolean save(RoleBO roleBO);
    Boolean update(RoleBO roleBo);
    Boolean deleteMany(String[] ids);
    List<Monitor> showMonitors(Integer id);
    List<Role> getRoles(String monitor);
}
