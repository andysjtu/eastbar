/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.account.dao;

import org.eastbar.web.account.entity.User;
import org.eastbar.web.annotation.MyBatisRepository;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月17
 * @time 下午1:56
 * @description :通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 */
@MyBatisRepository
public interface UserDao {
    User getUser(Integer id);
    User getUserRole(Integer id);
    User findByLoginName(String loginName);
    List<User> getAllUser(Map<String, Object> attr);
    Long getAllUserCount(Map<String, Object> attr);
    void save(User user);
    void delete(Integer id);
    void update(User user);
    void saveRelativity(Integer userId, String roleId);
}
