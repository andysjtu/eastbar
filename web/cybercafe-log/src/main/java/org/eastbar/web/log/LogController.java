/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.log;

import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.MonitorLiveDataService;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.SiteLiveDataService;
import org.eastbar.web.ipc.SiteRepairInfoService;
import org.eastbar.web.ipc.biz.MonitorLiveDataBO;
import org.eastbar.web.ipc.biz.SiteLiveDataBO;
import org.eastbar.web.ipc.biz.SiteRepairInfoBO;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.log.biz.MonitorCmdBO;
import org.eastbar.web.log.biz.OperLogBO;
import org.eastbar.web.syslog.SysLogService;
import org.eastbar.web.syslog.biz.SysLogBO;
import org.eastbar.web.syslog.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping(value="/log")
public class LogController {

    @Autowired
    private OperLogService operLogService;

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private MonitorLiveDataService monitorLiveDataService;

    @Autowired
    private SiteLiveDataService siteLiveDataService;

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private SiteRepairInfoService siteRepairInfoService;

    /**
     * 进入系统运行维护日志列表页面
     * @param sysLogBO
     * @return
     */
    @RequestMapping("/sysLog")
    public String submitLogSys(@ModelAttribute SysLogBO sysLogBO){
        return "log/logSysResult";
    }

    /**
     * 页面异步调用json，获取系统运行维护日志列表数据
     * @param sysLogBO
     * @return
     */
    @RequestMapping("/syslogjson")
    @ResponseBody
    public String syslogjson(@ModelAttribute SysLogBO sysLogBO){
        String json=null;
        try{
            PageInfo pr=sysLogService.getAll(sysLogBO);
            //PageInfo pr=operLogService.getAllAdmin(operLogBO);
            json= PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 进入管理员操作日志列表页面
     * @param monitorCmdBO
     * @return
     */
    @RequestMapping("/adminLog")
    public String submitLogAdmin(@ModelAttribute MonitorCmdBO monitorCmdBO){
        return "log/logAdminResult";
    }

    /**
     * 页面异步调用json，获取管理员操作日志列表数据
     * @param operLogBO
     * @return
     */
    @RequestMapping("/adminlogjson")
    @ResponseBody
    public String adminlogjson(@ModelAttribute OperLogBO operLogBO){
        String json=null;
        try{
            PageInfo pr=operLogService.getAll(operLogBO);
            json= PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 进入监管系统历史在线信息列表页面
     * @param monitorLiveDataBO
     * @return
     */
    @RequestMapping("/monitor")
    public String monitor(@ModelAttribute MonitorLiveDataBO monitorLiveDataBO){
        return "log/logMonitor";
    }

    @RequestMapping("/submitLogMonitor")
    public String submitLogMonitor(@ModelAttribute MonitorLiveDataBO monitorLiveDataBO){
        return "log/logMonitorResult";
    }

    /**
     * 页面异步调用json，获取监管系统历史在线信息列表页面
     * @param monitorLiveDataBO
     * @return
     */
    @RequestMapping("/monitorjson")
    @ResponseBody
    public String monitorjson(@ModelAttribute MonitorLiveDataBO monitorLiveDataBO){
        String json=null;
        try{
            PageInfo pr=monitorLiveDataService.getAll(monitorLiveDataBO);
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * 进入场所维修信息查询页面
     * @return
     */
    @RequestMapping("/place")
    public String place(@ModelAttribute SiteRepairInfoBO siteRepairInfoBO,Model model){
		List<Monitor> monitors=monitorService.getMonitors();
		model.addAttribute("monitors",monitors);
        return "log/logPlaceResult";
    }

    /**
     * 提交场所维修信息查询结果
     * @return
     */
    @RequestMapping("/placeJson")
	@ResponseBody
    public String submitLogPlace(@ModelAttribute SiteRepairInfoBO siteRepairInfoBO){
		String json = null;
		try{
			PageInfo pr = siteRepairInfoService.getAll(siteRepairInfoBO);
			json = PR2Json.go(pr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return json;
    }

    /**
     * 进入离线日志列表页面
     * @param siteLiveDataBO
     * @return
     */
    @RequestMapping("/submitLogOutLine")
    public String submitLogOutLine(@ModelAttribute SiteLiveDataBO siteLiveDataBO){
        return "log/logOutLineResult";
    }

    /**
     * 页面异步调用json，获取离线日志列表数据
     * @param siteLiveDataBO
     * @return
     */
    @RequestMapping("/outlinejson")
    @ResponseBody
    public String sitejson(@ModelAttribute SiteLiveDataBO siteLiveDataBO){
        String json=null;
        try{
            PageInfo pr=siteLiveDataService.getAll(siteLiveDataBO);
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     *进入部级日志查询页面
     * @return
     */
    @RequestMapping("/depart")
    public String depart(){
        return "log/logDepart";
    }


    /**
     * 进入部级日志列表页面
     * @return
     */
    @RequestMapping("/submitLogDepart")
    public String submitLogDepart(){
        return "log/logSys";
    }
}
