/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.msgman;

import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.msgman.biz.NoticeBO;
import org.eastbar.web.msgman.biz.NoticeRecoveryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
@RequestMapping(value="/msgman")
public class MsgManController {
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private NoticeRecoveryService noticeRecoveryService;
    @Autowired
    private MonitorService monitorService;

    @RequestMapping("/list")
    public String list(){
        return "msgman/msgmanList";
    }

    @RequestMapping("/public")
    public String publicList(@ModelAttribute NoticeBO noticeBO){
        return "msgman/msgmanPublicList";
    }

    @RequestMapping("/publicjson")
    @ResponseBody
    public String publicjson(@ModelAttribute NoticeBO noticeBO){
        String json=null;
        try{
            PageInfo pr=noticeService.getAllPublic(noticeBO);
            json= PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/receive")
    public String receiveList(@ModelAttribute NoticeBO noticeBO){
        return "msgman/msgmanReceiveList";
    }

    @RequestMapping("/receivejson")
    @ResponseBody
    public String receivejson(@ModelAttribute NoticeBO noticeBO){
        String json=null;
        try{
            List<Monitor> monitorList=monitorService.getUserMonitors();
            if(monitorList.size()>0){
                noticeBO.setMonitorCode(monitorList.get(0).getMonitorCode());
            }else{
                //noticeBO.setMonitorCode("3101");
            }
            PageInfo pr=noticeService.getAllReceive(noticeBO);
            json= PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/detail/{id}")
    public String detail(){
        return "msgman/msgmanDetail";
    }

    @RequestMapping("/intoAdd")
    public String intoAdd(){
        return "msgman/msgmanAdd";
    }

    @RequestMapping("/tree")
    @ResponseBody
    public String tree(){
        String json=null;
        try{
            List<Monitor> monitorList=monitorService.getMonitors();
            List<List<Monitor>> lists=new ArrayList<>();
            for(int i=0;i<monitorList.size();i++){
                List<Monitor> monitors=monitorService.getMonitorListByParent(monitorList.get(i).getMonitorCode());
                lists.add(monitors);
            }
            json= PR2Json.toTree(monitorList,lists);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/submitAdd")
    public String submitAdd(@ModelAttribute NoticeBO noticeBO,RedirectAttributes model){
        if(noticeService.save(noticeBO)){
            model.addAttribute("loadmsg","保存成功");
        }else{
            model.addAttribute("loadmsg","保存失败");
        }
        return "redirect:/msgman/public#_2";
    }

    @RequestMapping("/deleteMany/{ids}")
    public Map<String,String> deleteMany(@PathVariable String[] ids,RedirectAttributes model){
        Map<String,String> msg=new HashMap<>();
        if(noticeService.deleteMany(ids)){
            msg.put("msg","批量删除成功");
        }else{
            msg.put("msg","批量删除失败");
        }
        return msg;
    }


    @RequestMapping("/intoEdit/{id}")
    public String intoEdit(@PathVariable Integer id,Model model){
        NoticeBO noticeBO=noticeService.get(id);
        model.addAttribute("noticeBO",noticeBO);
        return "msgman/msgmanEdit";
    }

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable Integer id,RedirectAttributes model){
        if(noticeService.delete(id)){
            model.addAttribute("loadmsg","删除成功");
        }else{
            model.addAttribute("loadmsg","删除失败");
        }
        return "redirect:/msgman/public#_2";
    }


    @RequestMapping("/submitEdit")
    public String submitEdit(@ModelAttribute NoticeBO noticeBO,RedirectAttributes model){
        if(noticeService.update(noticeBO)){
            model.addAttribute("loadmsg","修改成功");
        }else{
            model.addAttribute("loadmsg","修改失败");
        }
        return "redirect:/msgman/public#_2";
    }

    @RequestMapping("/publicResponse/{id}")
    public String publicResponse(@PathVariable Integer id,Model model){
        model.addAttribute("id",id);
        return "msgman/msgmanPublicResponse";
    }

    @RequestMapping("/publicresponsejson/{id}")
    @ResponseBody
    public String publicResponseJson(@ModelAttribute NoticeRecoveryBO noticeRecoveryBO,@PathVariable Integer id){
        String json=null;
        try{
            noticeRecoveryBO.setNoticeId(id);
            PageInfo pr=noticeRecoveryService.getAll(noticeRecoveryBO);
            json=PR2Json.go(pr);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/receiveResponse")
    public String response(){
        return "msgman/msgmanReceiveResponse";
    }

}
