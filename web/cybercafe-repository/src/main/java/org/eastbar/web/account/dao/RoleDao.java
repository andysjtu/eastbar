/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.account.dao;

import org.eastbar.web.account.entity.Role;
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
public interface RoleDao {
    Role get(Integer id);
    Role getRoleMonitor(Integer id);
    void delete(Integer id);
    void deleteByMonitorCode(String monitorCode);
    List<Role> getAllRole(Map<String, Object> attr);
    Long getAllRoleCount(Map<String, Object> attr);
    List<Role> getRoles();
    List<Role> byMonitorCode(String monitorCode);
    void save(Role role);
    void update(Role role);
}
