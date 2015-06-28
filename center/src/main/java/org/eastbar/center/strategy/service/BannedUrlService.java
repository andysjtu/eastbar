/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy.service;

import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月13
 * @time 下午3:37
 * @description :
 */
public interface BannedUrlService {
    //中心端往redis存储版本信息时，使用这个方法,返回版本列表信息
    Map<String,Map<String,String>> control(Integer params) throws Exception;

    //场所端从数据库拉版本信息时，使用这个方法，返回版本列表信息
    String siteControl(String siteCode,Integer version) throws Exception;

}
