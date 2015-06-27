/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.centers.strategy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年05月29
 * @time 上午10:04
 * @description : 主要定义场所端发来请求的一些处理方法（可任意取调用redis，rmi，数据库等）
 */
@Service
public interface SiteStrategyEvent {

    /**
     * 根据场所提供的场所编码和版本号，读取相关list作为返回值
     * @param siteCode  verNum
     * @return
     */
    public String returnProgList(String siteCode,int verNum) throws Exception;

    /**
     * 根据场所提供的场所编码和版本号，读取相关list作为返回值
     * @param  siteCode  verNum
     * @return
     */
    public String returnUrlList(String siteCode,int verNum) throws Exception;

    /**
     * 根据场所提供的场所编码和版本号，读取相关list作为返回值
     * @param  siteCode  verNum
     * @return
     */
    public String returnKeyWordList(String siteCode,int verNum) throws Exception;

    /**
     * 根据场所提供的场所编码和版本号，读取相关list作为返回值
     * @param  siteCode  verNum
     * @return
     */
    public String returnSpecialCustomerList(String siteCode,int verNum) throws Exception;

    /**
     * 根据场所提供的策略名称,返回当前redis或者数据库对应的最新版本号
     * @param measure  通过帮助类Strategy常量获取,比如Strategy.BANNEDPROG
     * @return
     */
    public int lastedVersion(String measure) throws Exception;

    /**
     * 一次获取所有的最新策略版本号
     * @return
     * @throws Exception
     */
    public Map lastedVersions() throws Exception;

    /**
     *根据场所返回的操作结果，写库prog
     * @param
     */
    public int writeSiteProgVersion(String siteCode,int verNum);

    /**
     *根据场所返回的操作结果，写库url
     * @param
     */
    public int writeSiteUrlVersion(String siteCode,int verNum);

    /**
     *根据场所返回的操作结果，写库special
     * @param
     */
    public int writeSiteSpecialVersion(String siteCode,int verNum);

    /**
     *根据场所返回的操作结果，写库keyword
     * @param
     */
    public int writeSiteKeywordVersion(String siteCode,int verNum);

    /**
     * 场所提供刷新本地list，并将操作结果返回
     * @param list
     * @return
     */
    public int refreshList(List<String> list);

}
