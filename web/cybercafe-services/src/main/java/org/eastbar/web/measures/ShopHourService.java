/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures;

import org.eastbar.web.measures.biz.ShopHourBO;
import org.eastbar.web.measures.entity.ShopHour;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2014年10月14
 * @time 上午9:22
 * @description :
 */
public interface ShopHourService {

    ShopHourBO getShopHour(Integer id);
    List<ShopHour> getAllShopHour();
    Boolean delete(Integer id);
    Boolean update(ShopHourBO shopHourBO);
    Boolean save(ShopHourBO shopHourBO);
    List<ShopHour> getShopHourByType(Integer shopHourType);
    List<ShopHour> getSome(ShopHourBO shopHourBO);
    Boolean publish(Integer id);
    Boolean releaseMany(String[] ids);
}
