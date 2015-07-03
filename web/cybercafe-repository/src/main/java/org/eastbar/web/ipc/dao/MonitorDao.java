/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.ipc.entity.Monitor;

import java.util.List;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月14
 * @time 下午4:46
 * @description :
 */
@MyBatisRepository
public interface MonitorDao {

    Monitor get(String monitorCode);
    List<Monitor> byParentCode(Map<String, Object> attr);
    List<Monitor> byParentCodeSe(Map<String, Object> attr);
    void save(Monitor monitor);
    void delete(String monitorCode);
    void update(Monitor monitor);
    void deleteByParent(String parentCode);

	List<Monitor> getArea();

}
