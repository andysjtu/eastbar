/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.util;

/**
 * @author cindy-jia
 * @date 2015年03月12
 * @time 下午4:36
 * @description :
 */
public class RedisKey {


    /**
     * 生成一级监管中心的key
     * @param monitorCode
     * @return
     */
    public static String createCenterKey(String monitorCode){
        return "center:"+monitorCode+":live";
    }

    /**
     * 生成二级监管中心的key
     * @param monitorCode
     * @return
     */
    public static String createMonitorKey(String monitorCode){
        return "monitor:"+monitorCode+":live";
    }

    /**
     * 生成场所的key
     * @param siteCode
     * @return
     */
    public static String createSiteKey(String siteCode){
        return "site:"+siteCode+":live";
    }

    /**
     * 生成终端的hash的key
     * @param siteCode
     * @param ip
     * @return
     */
    public static String createTerminalKey(String siteCode,String ip){
        return "terminal:"+siteCode+":"+ip+":live";
    }

    /**
     * 生成终端的set的key
     * @param siteCode
     * @return
     */
    public static String createSetTerminalKey(String siteCode){
        return "terminal:"+siteCode+":host";
    }


}
