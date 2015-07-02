/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account.dao;

import org.eastbar.web.account.entity.UserRole;
import org.eastbar.web.annotation.MyBatisRepository;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月27
 * @time 上午11:42
 * @description :
 */
@MyBatisRepository
public interface UserRoleDao {

    void save(UserRole userRole);
    void delete(UserRole userRole);
    List<UserRole> get(Integer userId);
}
