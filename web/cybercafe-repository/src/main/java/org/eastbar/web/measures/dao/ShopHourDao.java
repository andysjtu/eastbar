/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures.dao;

import org.eastbar.web.annotation.MyBatisRepository;
import org.eastbar.web.measures.entity.ShopHour;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:06
 * @description :
 */
@MyBatisRepository
public interface ShopHourDao {

    ShopHour getShopHour(Integer id);
    List<ShopHour> getAllShopHour();
    void delete(Integer id);
    void update(ShopHour shopHour);
    void save(ShopHour shopHour);
    List<ShopHour> getShopHourByType(Integer shopHourType);
    List<ShopHour> getSome(ShopHour shopHour);
}
