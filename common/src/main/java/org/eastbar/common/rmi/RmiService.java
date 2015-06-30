/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.rmi;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年04月29
 * @time 下午3:07
 * @description :
 */
//@Service
public interface RmiService {

    /**
     * 关机
     * @param siteCode
     * @param ip
     * @return  0：成功/确认；1:失败； 2:消息有误；3:不支持；4:处理确认
     */
    public int shutDown(String siteCode, String ip);

    /**
     * 重启
     * @param siteCode
     * @param ip
     * @return
     */
    public int restart(String siteCode, String ip);

    /**
     * 锁定
     * @param siteCode
     * @param ip
     * @return
     */
    public int locking(String siteCode, String ip);

    /**
     * 解锁
     * @param siteCode
     * @param ip
     * @return
     */
    public int Unlock(String siteCode, String ip);

    /**
     * 截图
     * @param siteCode
     * @param ip
     * @return
     */
    public byte[] Screenshot(String siteCode, String ip);



    /**
     * 发送特殊人员版本号
     * @param version
     * @return
     */
    public int sendSpecialCustomerVersion(int version,Integer[] id,String operation)throws RuntimeException;

    public int sendSpecialCustomerVersion(int version)throws RuntimeException;



    /**
     * 发送关键字版本号
     * @param version
     * @return
     */
    public int sendKeyWordVersion(int version,Integer[] id,String operation)throws RuntimeException;

    public int sendKeyWordVersion(int version)throws RuntimeException;



    /**
     * 发送禁止url版本号
     * @param version
     * @return
     */
    public int sendBannedUrlVersion(int version,Integer[] id,String operation)throws RuntimeException;

    public int sendBannedUrlVersion(int version)throws RuntimeException;



    /**
     * 发送禁止程序版本号
     * @param version
     * @return
     */
    public int sendBannedProgVersion(int version,Integer[] id,String operation)throws RuntimeException;

    public int sendBannedProgVersion(int version)throws RuntimeException;

}

