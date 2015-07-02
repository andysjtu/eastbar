/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.ipc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eastbar.common.rmi.RmiService;
import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.Times;
import org.eastbar.web.ipc.biz.MonitorBO;
import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.biz.TerminalBO;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.log.MonitorCmdService;
import org.eastbar.web.log.OperLogService;
import org.eastbar.web.log.biz.MonitorCmdBO;
import org.eastbar.web.log.biz.OperLogBO;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.mapper.JsonMapper;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping(value="/ipc")
public class IpcController {

    @Autowired
    private MonitorService monitorService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private TerminalLogService terminalLogService;
    @Autowired
    private RmiService rmiService;
    @Autowired
    private OperLogService operLogService;
    @Autowired
    private MonitorCmdService monitorCmdService;

    private final static Logger logger= LoggerFactory.getLogger(IpcController.class);

    /*监管中心begin*/

    /**
     * 进入监管中心列表页面
     * @param monitorBO
     * @return
     */
    @RequestMapping("/list")
    public String list(@ModelAttribute MonitorBO monitorBO){

        return "ipc/ipcList";
    }

    /**
     * 异步调用json，返回监管中心列表数据
     * @param monitorBO
     * @return
     */
    @RequestMapping("/ipcjson/{parentCode}")
    @ResponseBody
    public String ipcjson(@ModelAttribute MonitorBO monitorBO){
        String json = null;
        try{
            PageInfo pr = monitorService.byParentCode(monitorBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入监管中心添加页面
     * @return
     */
    @RequestMapping("/intoAdd")
    public String intoAdd(Model model){
        List<Monitor> monitorList=monitorService.getUserMonitors();
        int grade=1;
        if(monitorList.size()==0){
           grade=0;
        }
        model.addAttribute("grade",grade);
        return "ipc/ipcAdd";
    }

    /**
     * 提交添加监管中心信息，返回监管中心列表页面
     * @param monitorBO
     * @param model
     * @return
     */
    @RequestMapping("/submitAdd")
    public String submitAdd(@ModelAttribute MonitorBO monitorBO,RedirectAttributes model){
        if( monitorService.save(monitorBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
            return "";
        }
        return "redirect:/ipc/list#_0";
    }

    /**
     * 进入修改监管中心信息页面
     * @param monitorCode
     * @param model
     * @return
     */
    @RequestMapping("/intoEdit/{monitorCode}")
    public String intoEdit(@PathVariable String monitorCode,Model model){
        MonitorBO monitorBO=monitorService.get(monitorCode);
        model.addAttribute("monitorBO",monitorBO);
        return "ipc/ipcEdit";
    }

    /**
     * 提交修改监管中心信息页面，返回监管中心列表页面
     * @param monitorBO
     * @param model
     * @return
     */
    @RequestMapping("/submitEdit")
    public String submitEdit(@ModelAttribute MonitorBO monitorBO,RedirectAttributes model){
        if(monitorService.update(monitorBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else {
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/ipc/list#_0";
    }

    /**
     * 删除监管中心
     * @param monitorCode
     * @param model
     * @return
     */
    @RequestMapping("/remove/{monitorCode}")
    public String remove(@PathVariable String monitorCode,RedirectAttributes model){
        if(monitorService.delete(monitorCode)){
            model.addFlashAttribute("loadmsg", "删除成功");
        }else{
            model.addFlashAttribute("loadmsg", "删除失败");
        }
        return "redirect:/ipc/list#_0";
    }

    /**
     * 批量删除监管中心
     * @param monitorCodes
     * @return
     */
    @RequestMapping("/deleteManyIpc/{monitorCodes}")
    @ResponseBody
    public Map<String, String> deleteManyIpc(@PathVariable String monitorCodes){

        String[] monitorCodes1=monitorCodes.split(",");
        Map<String,String> msg=new HashMap<>();
        if(monitorService.deleteMany(monitorCodes1)){
            msg.put("msg","批量删除成功");

        }else{
            msg.put("msg","批量删除失败");
        }
            return msg;

    }

/*监管中心 end*/

/*营业场所 begin*/

    /**
     * 根据监管中心编码，进入对应的场所列表页面
     * @param monitorCode
     * @param model
     * @return
     */
    @RequestMapping("/placeList/{monitorCode}")
    public String placeList(@PathVariable String monitorCode,Model model){
        model.addAttribute("monitorCode",monitorCode);
        return "ipc/placeList";
    }

    /**
     * 根据监管中心编码，异步调用json，得到对应的场所列表数据
     * @param siteBO
     * @return
     */
    @RequestMapping("/placejson/{monitorCode}")
    @ResponseBody
    public String placejson(@ModelAttribute SiteBO siteBO){
        String json = null;
        try{
            PageInfo pr = siteService.byMonitorCode(siteBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入添加场所页面
     * @param model
     * @return
     */
    @RequestMapping("/intoPlaceAdd")
    public String placeAdd(Model model){
        List<Monitor> monitors=monitorService.getPlaceMonitors();
        model.addAttribute("monitors",monitors);
        return "ipc/placeAdd";
    }

    /**
     * 提交添加场所信息，返回场所列表页面
     * @param siteBO
     * @param model
     * @return
     */
    @RequestMapping("/submitPlaceAdd")
    public String submitPlaceAdd(@ModelAttribute SiteBO siteBO,RedirectAttributes model){
        if(siteService.save(siteBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/ipc/placeList/-1#_0";

    }

    /**
     * 进入修改场所页面
     * @param siteCode
     * @param model
     * @return
     */
    @RequestMapping("/placeEdit/{siteCode}")
    public String placeEdit(@PathVariable String siteCode,Model model){
        SiteBO siteBO=siteService.get(siteCode);
        model.addAttribute("site",siteBO);
        return "/ipc/placeEdit";
    }

    /**
     * 提交修改场所信息，返回场所列表页面
     * @param siteBO
     * @param model
     * @return
     */
    @RequestMapping("/submitPlaceEdit")
    public String submitPlaceEdit(@ModelAttribute SiteBO siteBO,RedirectAttributes model){
        if(siteService.update(siteBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/ipc/placeList/-1#_0";
    }

    /**
     * 删除场所信息
     * @param siteCode
     * @param model
     * @return
     */
    @RequestMapping("/placeRemove/{siteCode}")
    public String placeRemove(@PathVariable String siteCode,RedirectAttributes model){
        if(siteService.delete(siteCode)){
            model.addFlashAttribute("loadmsg", "删除成功");
        }else{
            model.addFlashAttribute("loadmsg", "删除失败");
        }
        return "redirect:/ipc/placeList/-1#_0";
    }

    /**
     * 批量删除场所信息
     * @param siteCodes
     * @return
     */
    @RequestMapping("/deleteManyPlace/{siteCodes}")
    @ResponseBody
    public Map<String, String> deleteManyPlace(@PathVariable String siteCodes){

        String[] monitorCodes1=siteCodes.split(",");
        Map<String,String> msg=new HashMap<>();
        if(siteService.deleteMany(monitorCodes1)){
            msg.put("msg","批量删除成功");
        }else{
            msg.put("msg","批量删除失败");
        }

        return msg;

    }
    /*营业场所 end*/
    /*在线用户查询 begin*/

    /**
     * 进入在线用户查找页面
     * @param siteCode
     * @param model
     * @param terminalBO
     * @return
     */
    @RequestMapping("/onlineUser/{siteCode}")
    public String onlineUser(@PathVariable String siteCode,Model model,@ModelAttribute TerminalBO terminalBO){
        model.addAttribute("siteCode",siteCode);
        return "/ipc/onlineUser";
    }

    /**
     * 异步调用json，获取在线用户信息
     * @param terminalBO
     * @return
     */
    @ResponseBody
    @RequestMapping("/onlineUserjson/{siteCode}")
    public String onlineUserJson(@ModelAttribute TerminalBO terminalBO){
        String json = null;
        try{
            PageInfo pr = terminalLogService.getOnlineTerminalByBO(terminalBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }
   /*在线用户查询 end*/

    /**
     *根据场所编码，获取对应的终端信息
     * @param siteCode
     * @param model
     * @return
     */
    @RequestMapping("/siteTerminal/{siteCode}")
    public String siteTerminal(@PathVariable String siteCode,Model model){
        TerminalBO tb = terminalLogService.getSiteTerminalInfo(siteCode);
        model.addAttribute("tb",tb);

        List<TerminalBO> tbs = terminalLogService.getOnlineTerminal(siteCode);

        model.addAttribute("tbs",tbs);
        String json  = PR2Json.go(tbs);
        model.addAttribute("json",json);
        return "ipc/siteTerminalList";
    }

    /**
     * 下达关机命令，通知中心服务器
     */
    private final static String RMIERROR = "Connection refused: connect.";
    @RequestMapping("/{siteCode}/gj/{ip}/")
    @ResponseBody
    @Transactional
    public String shutdown(@PathVariable String siteCode,@PathVariable String ip){
        try{
            MonitorCmdBO monitorCmdBO=setTerminalBoValue(ip,0);
            int i =rmiService.shutDown(siteCode, monitorCmdBO.getHostIp());
            monitorCmdBO.setIsSuccess(i);
            monitorCmdService.save(monitorCmdBO);
            OperLogBO operLogBO=new OperLogBO();
            operLogBO.setOperTime(Times.now());
            operLogBO.setOperType(0);
            operLogBO.setCmdId(monitorCmdBO.getId());
            operLogBO.setUserId(ShiroCustomUtils.getCurrentID());
            operLogBO.setOperLog("对"+siteCode+"的"+monitorCmdBO.getHostIp()+"执行了关机操作");
            operLogService.add(operLogBO);
            return "IP:"+monitorCmdBO.getHostIp()+" Shutdown State: "+msgTransform(i)+" .";
        }catch (Exception e){
            return RMIERROR;
        }
    }

    /**
     * 下达重启命令，通知中心服务器
     * @param siteCode
     * @param ip
     * @return
     */
    @RequestMapping("/{siteCode}/cq/{ip}/")
    @ResponseBody
    @Transactional
    public String restart(@PathVariable String siteCode,@PathVariable String ip){
        try{
            MonitorCmdBO monitorCmdBO=setTerminalBoValue(ip,1);
            int i =rmiService.restart(siteCode, monitorCmdBO.getHostIp());
            monitorCmdBO.setIsSuccess(i);
            monitorCmdService.save(monitorCmdBO);
            OperLogBO operLogBO=new OperLogBO();
            operLogBO.setOperTime(Times.now());
            operLogBO.setOperType(1);
            operLogBO.setUserId(ShiroCustomUtils.getCurrentID());
            operLogBO.setOperLog("对"+siteCode+"的"+monitorCmdBO.getHostIp()+"执行了重启操作");
            operLogService.add(operLogBO);
            return "IP:"+monitorCmdBO.getHostIp()+" Restart State: "+msgTransform(i)+" .";
        }catch (Exception e){
            return RMIERROR;
        }
    }

    /**
     * 下达锁定命令，通知中心服务器
     * @param siteCode
     * @param ip
     * @return
     */
    @RequestMapping("/{siteCode}/sd/{ip}/")
    @ResponseBody
    public String lock(@PathVariable String siteCode,@PathVariable String ip){
        try{
            MonitorCmdBO monitorCmdBO=setTerminalBoValue(ip,2);
            int i =rmiService.locking(siteCode,monitorCmdBO.getHostIp());
            monitorCmdBO.setIsSuccess(i);
            monitorCmdService.save(monitorCmdBO);
            OperLogBO operLogBO=new OperLogBO();
            operLogBO.setOperTime(Times.now());
            operLogBO.setOperType(2);
            operLogBO.setUserId(ShiroCustomUtils.getCurrentID());
            operLogBO.setOperLog("对"+siteCode+"的"+monitorCmdBO.getHostIp()+"执行了锁定操作");
            operLogService.add(operLogBO);
            return "IP:"+monitorCmdBO.getHostIp()+" Locking State: "+msgTransform(i)+" .";
        }catch (Exception e){
            return RMIERROR;
        }
    }

    /**
     * 下达解锁命令，通知中心服务器
     * @param siteCode
     * @param ip
     * @return
     */
    @RequestMapping("/{siteCode}/js/{ip}/")
    @ResponseBody
    public String unlock(@PathVariable String siteCode,@PathVariable String ip){
        try{
            MonitorCmdBO monitorCmdBO=setTerminalBoValue(ip,3);
            int i =rmiService.Unlock(siteCode, monitorCmdBO.getHostIp());
            monitorCmdBO.setIsSuccess(i);
            monitorCmdService.save(monitorCmdBO);
            OperLogBO operLogBO=new OperLogBO();
            operLogBO.setOperTime(Times.now());
            operLogBO.setOperType(3);
            operLogBO.setUserId(ShiroCustomUtils.getCurrentID());
            operLogBO.setOperLog("对"+siteCode+"的"+monitorCmdBO.getHostIp()+"执行了解锁操作");
            operLogService.add(operLogBO);
            return "IP:"+monitorCmdBO.getHostIp()+" Unlock State: "+msgTransform(i)+" .";
        }catch (Exception e){
            return RMIERROR;
        }
    }

    /**
     *获取终端的详细信息
     * @param siteCode
     * @param ip
     * @param model
     * @return
     */
    @RequestMapping("/{siteCode}/jp/{ip}/")
    public String capture(@PathVariable String siteCode,@PathVariable String ip,Model model){
        TerminalBO tb = terminalLogService.getSiteTerminalInfo(siteCode);
        tb.setHostIp(ip);
        model.addAttribute("tb",tb);
        return "ipc/capture";
    }

    /**
     * 下达截屏命令，通知中心服务器
     * @param siteCode
     * @param ip
     * @param response
     */
    @RequestMapping("/{siteCode}/jpdata/{ip}/")
    public void captureData(@PathVariable String siteCode,@PathVariable String ip,HttpServletResponse response){
        try{
            MonitorCmdBO monitorCmdBO=setTerminalBoValue(ip,4);
            byte[] bytePic = rmiService.Screenshot(siteCode,monitorCmdBO.getHostIp());
            response.reset();
            response.setContentType("image/JPEG");
            response.setHeader("CONTENT-DISPOSITION", "filename="+ Times.now()+".jpg");
            if (bytePic!= null && bytePic.length!=0)
            {
                response.getOutputStream().write(bytePic);
            }
            if(bytePic.length>0){
                monitorCmdBO.setIsSuccess(0);
            }else{
                monitorCmdBO.setIsSuccess(1);
            }
            monitorCmdService.save(monitorCmdBO);
            OperLogBO operLogBO=new OperLogBO();
            operLogBO.setOperTime(Times.now());
            operLogBO.setOperType(4);
            operLogBO.setUserId(ShiroCustomUtils.getCurrentID());
            operLogBO.setOperLog("对"+siteCode+"的"+monitorCmdBO.getHostIp()+"执行了截屏操作");
            operLogService.add(operLogBO);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private String msgTransform(int state){
        String msg;
        switch (state){
            case 0:msg="成功";break;
            case 1:msg="失败";break;
            case 2:msg="消息有误";break;
            case 3:msg="不支持";break;
            case 4:msg="处理确认";break;
            default:msg="UNKNOWN";
        }
        return msg;

    }

    //统计分析部分对监管中心的ajax获取
    @RequestMapping("/getMonitors/{parentCode}")
    @ResponseBody
    public String getMonitors(@PathVariable String parentCode){
        List<Monitor> list=monitorService.getMonitorListByParent(parentCode);
        String json= PR2Json.go(list);
        return json;
    }

    private MonitorCmdBO setTerminalBoValue(String json,int cmdType){
        MonitorCmdBO monitorCmdBO=new MonitorCmdBO();
        try{
            ObjectMapper objectMapper=new ObjectMapper();
            TerminalBO terminalBO=objectMapper.readValue(json,TerminalBO.class);
            monitorCmdBO.setHostIp(terminalBO.getHostIp());
            monitorCmdBO.setCmdTime(Times.now());
            monitorCmdBO.setCommander(ShiroCustomUtils.getCurrentUserName());
            monitorCmdBO.setCustomerId(terminalBO.getAccountId());
            monitorCmdBO.setCustomerIdType(terminalBO.getCustomerIdType());
            monitorCmdBO.setSiteCode(terminalBO.getSiteCode());
            monitorCmdBO.setCmdType(cmdType);
            if(terminalBO.getSiteCode()!=null && !"".equals(terminalBO)){
                String sCode=terminalBO.getSiteCode();
                String mCode=sCode.substring(0,6);
                monitorCmdBO.setMonitorCode(mCode);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return monitorCmdBO;
    }
}
