/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.account;

import org.eastbar.web.account.biz.UserRoleBO;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月27
 * @time 上午11:50
 * @description :
 */
public interface UserRoleService {

    Boolean save(UserRoleBO userRoleBO);
    Boolean delete(UserRoleBO userRoleBO);
    Boolean edit(UserRoleBO userRoleBO);
    List<UserRoleBO> get(Integer userId);
}
