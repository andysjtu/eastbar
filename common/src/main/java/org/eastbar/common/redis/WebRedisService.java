/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author cindy-jia
 * @date 2015年05月11
 * @time 上午10:21
 * @description :
 */
public interface WebRedisService {

    /**
     * 根据提供的监管中心编码获取一级监管中心的活动数据
     * @param monitorCode
     * @return
     */
    public Map getCeneterHash(String monitorCode);

    /**
     * 根据提供的监管中心编码获取二级监管中心的活动数据
     * @param monitorCode
     * @return
     */
    public  Map getMonitorHash(String monitorCode);

    /**
     * 根据场所的场所编码获取场所的活动数据
     * @param siteCode
     * @return
     */
    public  Map getSiteHash(String siteCode);

    /**
     * 根据场所编码获取ip集
     * @param siteCode
     * @return
     */
    public Set getIpSet(String siteCode);

    /**
     *根据场所ip集，获取场所内所有终端活动信息
     * @param ip
     * @param siteCode
     * @return
     */
    public Map getTerminalHash(String ip, String siteCode);

}
