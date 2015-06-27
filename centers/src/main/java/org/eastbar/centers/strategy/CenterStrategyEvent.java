/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.strategy;

import org.springframework.stereotype.Service;

/**
 * @author cindy-jia
 * @date 2015年05月29
 * @time 上午10:04
 * @description : 主要定义web的一些处理方法（可任意取调用redis，rmi，数据库等）
 */
@Service
public interface CenterStrategyEvent {

    /**
     * 发送特殊人员版本号
     * @param version
     * @return
     */
    public int sendSpecialCustomerVersion(int version);

    /**
     * 发送关键字版本号
     * @param version
     * @return
     */
    public int sendKeyWordVersion(int version);

    /**
     * 发送禁止url版本号
     * @param version
     * @return
     */
    public int sendBannedUrlVersion(int version);

    /**
     * 发送禁止程序版本号
     * @param version
     * @return
     */
    public int sendBannedProgVersion(int version);


    /**
     * 中心端定期刷prog库，
     * @return int result=0 刷库失败  result=1  刷库成功   result=-1 没有需要更新的信息
     * @throws Exception
     */
    public int regularFreshProgVersion() throws Exception;

    /**
     * 中心端定期刷url库，
     * @return int result=0 刷库失败  result=1  刷库成功   result=-1 没有需要更新的信息
     * @throws Exception
     */
    public int regularFreshUrlVersion() throws Exception;

    /**
     * 中心端定期刷specialcustomer库，
     * @return int result=0 刷库失败  result=1  刷库成功   result=-1 没有需要更新的信息
     * @throws Exception
     */
    public int regularFreshSpecialCustomerVersion() throws Exception;

    /**
     * 中心端定期刷keyword库，
     * @return int result=0 刷库失败  result=1  刷库成功   result=-1 没有需要更新的信息
     * @throws Exception
     */
    public int regularFreshKeyWordVersion() throws Exception;


}
