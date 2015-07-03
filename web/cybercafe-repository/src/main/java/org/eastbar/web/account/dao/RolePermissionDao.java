/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.dao;

import org.eastbar.web.account.entity.RolePermission;
import org.eastbar.web.annotation.MyBatisRepository;

/**
 * @author cindy-jia
 * @date 2014年10月29
 * @time 下午2:07
 * @description :
 */
@MyBatisRepository
public interface RolePermissionDao {

    void save(RolePermission rolePermission);
    void delete(Integer roleId);
    void update(RolePermission rolePermission);
}
