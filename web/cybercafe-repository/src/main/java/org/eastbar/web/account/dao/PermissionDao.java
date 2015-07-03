/********************************************************************************
 *                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
 *                          Copyright © 2003-2014                               *
 ********************************************************************************/
package org.eastbar.web.account.dao;

import org.eastbar.web.account.entity.Permission;
import org.eastbar.web.annotation.MyBatisRepository;

/**
 * @author C.lins@aliyun.com
 * @date 2014年08月17
 * @time 下午1:56
 * @description :通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 */
@MyBatisRepository
public interface PermissionDao {
    Permission get(Integer id);
    Permission byPermisson(String permission);

}
