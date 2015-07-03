/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account;

import org.eastbar.web.account.biz.RolePermissionBO;

/**
 * @author cindy-jia
 * @date 2014年10月29
 * @time 下午2:16
 * @description :
 */
public interface RolePermissionService {

    Boolean save(RolePermissionBO rolePermissionBO);
    Boolean delete(Integer roleId);
    Boolean updatePermissions(String[] permissions, Integer id);
}
