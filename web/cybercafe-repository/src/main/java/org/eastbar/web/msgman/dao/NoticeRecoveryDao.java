/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.msgman.entity.NoticeRecovery;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午3:41
 * @description :
 */
@MyBatisRepository
public interface NoticeRecoveryDao {
     List<NoticeRecovery> getAll(Map<String, Object> re);
     NoticeRecovery get(Integer id);
}
