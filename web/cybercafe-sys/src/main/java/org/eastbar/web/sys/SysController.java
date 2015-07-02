/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.sys;

import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.sys.biz.RankBO;
import org.eastbar.web.sys.entity.AlarmNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping(value="/sys")
public class SysController {

    @Autowired
    private AlarmNotifyService alarmNotifyService;

    @Autowired
    private RankService rankService;

    /*报警等级begin*/
    @RequestMapping("/alert")
    public String alert(){
        return "sys/sysAlert";
    }

    @RequestMapping("/alertjson")
    @ResponseBody
    public String alertjson(@ModelAttribute RankBO rankBO){
        String json = null;
        try{
            PageInfo pr = rankService.getAllRank(rankBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/intoAlertEdit/{id}")
    public String intoAlertEdit(@PathVariable Integer id,Model model){
        RankBO rankBO=rankService.get(id);
        model.addAttribute("rankBO",rankBO);
        return "sys/sysAlertEdit";
    }

    @RequestMapping("/submitAlertEdit")
    public String submitAlertEdit(@ModelAttribute RankBO rankBO,RedirectAttributes model){
        if(rankService.update(rankBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/sys/alert#_3";
    }
    /*报警等级end*/
    /*短消息报警通知 begin*/
    @RequestMapping("/sMs")
    public String sMs(ModelMap models,Model model){
        AlarmNotify alarmNotify1=new AlarmNotify();
        alarmNotify1.setNotifierType(1);
        List<AlarmNotify> alarmNotifiers=alarmNotifyService.getAlarmNotifysByCondition(alarmNotify1);
        AlarmNotify alarmNotify=new AlarmNotify();
        alarmNotify=alarmNotifyService.getAlarmNotify(1);
        model.addAttribute("alarmNotify",alarmNotify);
        if(alarmNotifiers.size()>0){
            models.addAttribute("alarmNotifiers",alarmNotifiers);
        }
        return "sys/sysSms";
    }

    @RequestMapping("/server")
    public String server(){
        return "sys/sysServer";
    }


    @RequestMapping("/intoSmsEdit/{id}")
    public String intoSmsEdit(@PathVariable Integer id,Model model){
        AlarmNotify alarmNotify=alarmNotifyService.getAlarmNotify(id);
        model.addAttribute("alarmNotify",alarmNotify);
        return "sys/sysSmsEdit";
    }

    @RequestMapping("/submitSmsEdit")
    public String submitSmsEdit(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){
        if(alarmNotifyService.update(alarmNotify)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/sys/sMs#_3";
    }

    @RequestMapping("/intoSmsAdd")
    public String intoSmsAdd(){
        return "sys/sysSmsAdd";
    }

    @RequestMapping("/submitSmsAdd")
    public String submitSmsAdd(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){
        alarmNotify.setNotifierType(1);
        if(alarmNotifyService.save(alarmNotify)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/sys/sMs#_3";
    }

    @RequestMapping("/intoSmsReceiverEdit/{id}")
    public String intoEmailSenderEdit(@PathVariable Integer id,Model model){
        AlarmNotify alarmNotify=alarmNotifyService.getAlarmNotify(id);
        model.addAttribute("alarmNotify",alarmNotify);
        return "sys/sysSmsReceiverEdit";
    }

    @RequestMapping("/submitSmsReceiverEdit")
    public String submitSmsSenderEdit(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){
        AlarmNotify alarmNotify1=alarmNotifyService.getAlarmNotify(alarmNotify.getId());
        alarmNotify1.setReceiver(alarmNotify.getReceiver());
        if(alarmNotifyService.update(alarmNotify1)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/sys/sMs#_3";

    }

    @RequestMapping("/removeSms/{id}")
    public String sysRemove(@PathVariable Integer id,RedirectAttributes model){
        if(alarmNotifyService.delete(id)){
            model.addFlashAttribute("loadmsg", "删除成功");
        }else{
            model.addFlashAttribute("loadmsg", "删除失败");
        }
        return "redirect:/sys/sMs#_3";
    }
    /*短消息报警通知 end*/
    /*邮件报警通知 begin*/
    @RequestMapping("/email")
    public String email(ModelMap models,Model model){
        AlarmNotify alarmNotify1=new AlarmNotify();
        alarmNotify1.setNotifierType(2);
        List<AlarmNotify> alarmNotifiers=alarmNotifyService.getAlarmNotifysByCondition(alarmNotify1);
        AlarmNotify alarmNotify=alarmNotifyService.getAlarmNotify(2);
        model.addAttribute("alarmNotify",alarmNotify);
        if(alarmNotifiers.size()>0){
            models.addAttribute("alarmNotifiers",alarmNotifiers);
        }
        return "sys/sysEmail";
    }

    @RequestMapping("/intoEmailServerEdit")
    public String intoEmailServerEdit(Model model){
        AlarmNotify alarmNotify1=alarmNotifyService.getAlarmNotify(2);
        model.addAttribute("alarmNotify",alarmNotify1);
        return "sys/sysEmailServerEdit";
    }

    @RequestMapping("/submitEmailServerEdit")
    public String submitEmailServerEdit(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){

         if(alarmNotifyService.update(alarmNotify)){
              model.addFlashAttribute("loadmsg", "修改成功");
         }else {
              model.addFlashAttribute("loadmsg", "修改失败");
         }
        return "redirect:/sys/email#_3";
    }

    @RequestMapping("/intoEmailSenderEdit")
    public String intoEmailSenderEdit(Model model){
        AlarmNotify alarmNotify=alarmNotifyService.getAlarmNotify(2);
        model.addAttribute("alarmNotify",alarmNotify);
        return "sys/sysEmailSenderEdit";
    }

    @RequestMapping("/submitEmailSenderEdit")
    public String submitEmailSenderEdit(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){
        if(alarmNotifyService.update(alarmNotify)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/sys/email#_3";
    }

    @RequestMapping("/intoEmailReceiverAdd")
    public String intoEmailReceiverAdd(){
        return "sys/sysEmailReceiverAdd";
    }

    @RequestMapping("/submitEmailReceiverAdd")
    public String submitEmailReceiverAdd(@ModelAttribute AlarmNotify alarmNotify,RedirectAttributes model){
        if(alarmNotifyService.save(alarmNotify)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/sys/email#_3";
    }


    @RequestMapping("/emailRemove/{id}")
    public String emailRemove(@PathVariable Integer id,RedirectAttributes model){
        if(alarmNotifyService.delete(id)){
            model.addFlashAttribute("loadmsg", "删除成功");
        }else{
            model.addFlashAttribute("loadmsg", "删除失败");
        }
        return "redirect:/sys/email#_3";
    }
    /*邮件报警通知 end*/

    @RequestMapping("/intoServerAdd")
    public String intoServerAdd(){
        return "sys/sysServerAdd";
    }

    @RequestMapping("/submitServerAdd")
    public String submitServerAdd(){
        return "redirect:/sys/server#_3";
    }

    @RequestMapping("/intoServerEdit")
    public String intoServerEdit(){
        return "sys/sysServerEdit";
    }

    @RequestMapping("/submitServerEdit")
    public String submitServerEdit(){
        return "redirect:/sys/server#_3";
    }

    @RequestMapping("/serverRemove")
    public String serverRemove(){
        return "redirect:/sys/server";
    }



}
