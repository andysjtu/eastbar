/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:21
 * @description :
 */
public interface SiteRedisService {

    /**
     * 根据场所编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    public String returnProgVersionList(String siteCode, Integer version) throws Exception;

    /**
     * 根据场所编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    public String returnUrlVersionList(String siteCode, Integer version) throws Exception;

    /**
     * 根据场所编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    public String returnKeyWordVersionList(String siteCode, Integer version) throws Exception;

    /**
     * 根据场所编码和版本号返回需更新的list
     * @param siteCode
     * @param version
     * @return
     * @throws Exception
     */
    public String returnSpecialCustomerVersionList(String siteCode, Integer version) throws Exception;


    /**
     * 根据条件找出最新版本号
     * @param meaure
     * @return
     * @throws Exception
     */
    public String returnLastedVersion(String meaure) throws Exception;


    /**
     * 根据条件找出最近更新的版本号
     * @param meaure
     * @return
     * @throws Exception
     */
    public String returnUpdatedVersion(String meaure) throws Exception;
}


