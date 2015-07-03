/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eastbar.web.ipc.biz.TerminalBO;
import org.springside.modules.mapper.JsonMapper;

/**
 * @author cindy-jia
 * @date 2015年07月02
 * @time 下午2:17
 * @description :
 */
public class Test {

    private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    public static void main(String[] args) throws Exception{
        String cmd="siteCode:3101010003,hostIp:192.168.9.100,certId:320107197902026432,onlineTime:20150701134045,siteState ONLINE,";
        cmd=cmd.substring(0,cmd.length()-1);
        TerminalBO terminalBO=new TerminalBO();
        terminalBO.setMonitorCode("3101");
        terminalBO.setAccountId("1223322");
        System.out.println(binder.toJson(terminalBO));

        ObjectMapper objectMapper=new ObjectMapper();
        TerminalBO terminalBO1=objectMapper.readValue("",TerminalBO.class);
        System.out.println("1");

    }
}
