package org.eastbar.web.service;

import java.util.Map;

/**
 * RMI 通讯接口
 * @author Gxx
 *
 */
public interface RMIService_bak {
	
	/**
	 * 关机
	 * return 0：成功/确认,
	 * 1:失败;
	 * 2:消息有误,
	 * 3:不支持；
	 * 4:处理确认
	 */
	public int Shutdown(String siteCode, String ip);
	
	/**
	 * 重启
	 */
	public int Restart(String siteCode, String ip);
	
	/**
	 * 锁定
	 */
	public int Locking(String siteCode, String ip);
	
	/**
	 * 解锁
	 */
	public int Unlock(String siteCode, String ip);
	
	/**
	 * 截图
	 */
	public byte[] Screenshot(String siteCode, String ip);
	
	/**
	 *特殊人员
	 */
	public int sendSpecialCustomer(String version);
	
	/**
	 * 关键字
	 */
	public int sendKeyWord(String version);
	
	/**
	 * 禁止URL
	 */
	public int sendBannedUrl(String version);
	
	/**
	 * 禁止程序
	 */
	public int sendBannedProg(String version);

	/**
	 * 市级监管中心
	 * 状态，场所总数，营业总数,终端总数，报警总数，处罚总数
	 * status,totalSite,openSite,totalTerminal,totalAlarm,totalPunish
	 * @return
	 */
	public Map<String, Map<String, Integer>> monirorCount();

	/**
	 * 场所信息
	 * 连接终端，本周报警，处罚数，客户端安装率
	 * connectTerm,totalAlarm,totalPunish,installationRate
	 * @return
	 */
	public Map<String, Map<String, Integer>> useCount();

	/**
	 * 运营状态
	 * 空闲，使用，锁定，关机
	 * freeHost,useHost,locking,close
	 * @return
	 */
	public Map<String, String> operationsCount(String siteCode);

}
