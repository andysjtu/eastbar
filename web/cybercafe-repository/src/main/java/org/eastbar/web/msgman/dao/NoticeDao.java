/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.msgman.entity.Notice;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2014年12月03
 * @time 下午3:38
 * @description :
 */
@MyBatisRepository
public interface NoticeDao {

    void save(Notice notice);
    void update(Notice notice);
    void delete(Integer id);
    Notice get(Integer id);
    List<Notice> getAllPublic(Map<String, Object> re);
    List<Notice> getAllReceive(Map<String, Object> re);
}
