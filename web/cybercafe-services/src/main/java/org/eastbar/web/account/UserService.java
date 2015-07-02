/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.account;

import org.eastbar.web.PageInfo;
import org.eastbar.web.account.biz.UserBO;
import org.eastbar.web.account.biz.UserRoleBO;
import org.eastbar.web.account.entity.User;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月17
 * @time 下午3:34
 * @description :
 */
public interface UserService {
    UserBO get(Integer id);
    Boolean save(UserBO userBO);
    Boolean delete(Integer id);
    PageInfo getAllUser(UserBO userBO);
    UserBO getUserRole(Integer id);
    Boolean update(UserBO userBO);
    Boolean deleteMany(String[] ids);
    Boolean deleteRoleAndUser(UserRoleBO userRoleBO);

    /** 用于安全效验 */
    User findByLoginName(String loginName);
    Boolean updateLoginInfo(User user);
}
