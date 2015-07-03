/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.measures;

import org.eastbar.common.rmi.RmiService;
import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.SiteService;
import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.measures.biz.*;
import org.eastbar.web.measures.entity.ShopHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping(value="/measures")
public class MeasuresController {

    @Autowired
    private ShopHourService shopHourService;

    @Autowired
    private BannedUrlService bannedUrlService;

    @Autowired
    private BannedProgService bannedProgService;

    @Autowired
    private SpecialCustomerService specialCustomerService;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private RmiService rmiService;

    @Autowired
    private KeyWordService keyWordService;

    @Autowired
    private ManageRuleService manageRuleService;

    /* 营业时间 begin*/

    /**
     * 进入场所营业时间列表页面
     * @param model
     * @return
     */
    @RequestMapping("/place")
    public String place(Model model){
        //根据营业时间类型来得到不同的list type=1是按周记  type=2是按日期计
        List<ShopHour> weeks=shopHourService.getShopHourByType(1);
        List<ShopHour> days=shopHourService.getShopHourByType(2);
        model.addAttribute("weeks",weeks);
        model.addAttribute("days",days);
        return "measures/measuresPlace";
    }

    /**
     * 进入添加场所营业时间页面
     * @return
     */
    @RequestMapping("/intoPlaceAdd")
    public String intoPlaceAdd(){
        return "measures/measuresPlaceAdd";
    }

    /**
     * 提交添加场所营业时间信息
     * @param shopHourBO
     * @param model
     * @param num
     * @param place
     * @return
     */
    @RequestMapping("/submitPlaceAdd/{num}/{place}")
    public String submitPlaceAdd(@ModelAttribute ShopHourBO shopHourBO,RedirectAttributes model,@PathVariable Integer num,@PathVariable Integer place){
       //num ==1 代表发布  num==0 代表不发布
        if(num==1)
            for(ShopHourBO shopHourBO1:shopHourBO.getShopHourBOList()){
                shopHourBO1.setIsPub(1);
            }
        //页面写好的三个营业时间规则的添加区域，有的显示有的隐藏，place是代表添加的个数-1
        if(place==0){//place==0代表只添加了一个营业时间规则
            shopHourBO.getShopHourBOList().remove(1);
            shopHourBO.getShopHourBOList().remove(1);
        }else if(place==1){//place==1 代表添加了两条营业时间规则
            shopHourBO.getShopHourBOList().remove(2);
        }else if(place==2){//place==2代表添加了三条营业时间规则
            shopHourBO.getShopHourBOList().remove(1);
        }else{

        }

        if(shopHourService.save(shopHourBO)){
            model.addFlashAttribute("loadmsg", "保存成功");

        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/measures/place#_4";
    }

    /**
     * 进入按周记的营业时间修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoPlaceWeekEdit/{id}")
    public String intoPlaceWeekEdit(@PathVariable Integer id,Model model){
        ShopHourBO shopHourBO=shopHourService.getShopHour(id);
        model.addAttribute("shopHourBO",shopHourBO);
        return "measures/measuresPlaceWeekEdit";
    }

    /**
     * 提交按周记的营业时间修改信息，并返回到营业时间列表
     * @param shopHourBO
     * @param model
     * @return
     */
    @RequestMapping("/submitPlaceWeekEdit")
    public String submitPlaceWeekEdit(@ModelAttribute ShopHourBO shopHourBO,RedirectAttributes model){
        shopHourBO.setIsPub(0);
        if(shopHourService.update(shopHourBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/place#_4";
    }

    /**
     *进入按日期记的营业时间修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoPlaceDateEdit/{id}")
    public String intoPlaceDateEdit(@PathVariable Integer id,Model model){
       ShopHourBO shopHourBO=shopHourService.getShopHour(id);
        model.addAttribute("shopHourBO",shopHourBO);
        return "measures/measuresPlaceDateEdit";
    }

    /**
     * 提交按日期记的营业时间修改的信息，并返回到营业时间列表
     * @param shopHourBO
     * @param model
     * @return
     */
    @RequestMapping("/submitPlaceDateEdit")
    public String submitPlaceDateEdit(@ModelAttribute ShopHourBO shopHourBO,RedirectAttributes model){
        shopHourBO.setIsPub(0);
        if(shopHourService.update(shopHourBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/place#_4";
    }

    /**
     * 删除一条营业时间规则
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/placeDelete/{id}")
    public String placeWeekDelete(@PathVariable Integer id,RedirectAttributes model){
        if(shopHourService.delete(id)){
            model.addFlashAttribute("loadmsg", "删除成功");
        }else{
            model.addFlashAttribute("loadmsg", "删除失败");
        }
        return "redirect:/measures/place#_4";
    }

    /**
     * 批量删除营业时间规则
     * @param ids
     * @return
     */
    @RequestMapping("/releaseManyPlace/{ids}")
    @ResponseBody
    public Map<String, String> releaseManyPlace(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        String[] sid=ids.split(",");
        if(shopHourService.releaseMany(sid)){
            msg.put("msg","批量发布成功");
        }else{
            msg.put("msg","批量发布成功");
        }
        return msg;
    }

    /**
     * 发布营业时间规则
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/placePublic/{id}")
    public String placePublic(@PathVariable Integer id,RedirectAttributes model){


        if(shopHourService.publish(id)){
            model.addFlashAttribute("loadmsg", "发布成功");
        }else{
            model.addFlashAttribute("loadmsg", "发布失败");
        }
        return "redirect:/measures/place#_4";
    }


    /* 营业时间 end*/

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
            json= PR2Json.toTree(monitorList, lists);
            System.out.println(json);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }
    /* 禁止URL begin*/

    /**
     * 进入url屏蔽库列表页面
     * @param bannedUrlBO
     * @return
     */
    @RequestMapping("/URL")
    public String url(@ModelAttribute BannedUrlBO bannedUrlBO){
        return "measures/measuresUrl";
    }

    /**
     * 异步调用json，返回url数据列表
     * @param bannedUrlBO
     * @return
     */
    @RequestMapping("/bannedurljson")
    @ResponseBody
    public String getBannedUrlList(@ModelAttribute BannedUrlBO bannedUrlBO){
        String json = null;
        try{
            PageInfo pr =bannedUrlService.getAllBannedUrl(bannedUrlBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入url修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoUrlEdit/{id}")
    public String intoUrlEdit(@PathVariable Integer id, Model model){
        BannedUrlBO bannedUrlBO=bannedUrlService.getBannedUrl(id);
        List<Monitor> monitors=monitorService.getPlaceMonitors();
        model.addAttribute("bannedUrlBO",bannedUrlBO);
        model.addAttribute("monitors",monitors);
        return "measures/measuresUrlEdit";
    }

    /**
     * 进入url添加页面
     * @param model
     * @return
     */
    @RequestMapping("/intoUrlAdd")
    public String intoUrlAdd(Model model){
        List<Monitor> monitors=monitorService.getPlaceMonitors();
        model.addAttribute("monitors",monitors);
        return "measures/measuresUrlAdd";
    }

    @RequestMapping("/sitelist/{monitorCode}")
    @ResponseBody
    public String siteList(@PathVariable String monitorCode){
        List<SiteBO> siteList=siteService.findListbyMonitorCode(monitorCode);
        String json=PR2Json.go(siteList);
        return json;
    }

    /**
     * 提交url添加的信息
     * @param bannedUrlBO
     * @param model
     * @return
     */
    @RequestMapping("/submitUrlAdd")
    public String submitUrlAdd(@ModelAttribute BannedUrlBO bannedUrlBO,RedirectAttributes model){
        if(bannedUrlService.save(bannedUrlBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
       }else{
            model.addFlashAttribute("loadmsg", "保存失败");
       }
        return "redirect:/measures/URL#_4";
    }

    /**
     * 提交url修改信息
     * @param bannedUrlBO
     * @param model
     * @return
     */
    @RequestMapping("/submitUrlEdit")
    public String submitUrlEdit(@ModelAttribute BannedUrlBO bannedUrlBO,RedirectAttributes model){
        //设置是否发布，默认不发布
        bannedUrlBO.setIsPub(0);
        if(bannedUrlService.update(bannedUrlBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/URL#_4";
    }

    /**
     * 删除单条url
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/urlDelete/{id}")
    public String urlDelete(@PathVariable Integer id,RedirectAttributes model){
       try{
           Integer[] ids={id};
           if(bannedUrlService.deleteMany(ids)){
               model.addFlashAttribute("loadmsg", "删除成功");
           }else{
               model.addFlashAttribute("loadmsg", "删除失败");
           }
       }catch (Exception e){
           e.printStackTrace();
           model.addFlashAttribute("loadmsg", "网络连接异常");

       }

        return "redirect:/measures/URL#_4";
    }

    /**
     * 批量删除url
     * @param ids
     * @return
     */
    @RequestMapping("/deleteManyUrl/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyUrl(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            if(bannedUrlService.deleteMany(idss)){
                msg.put("msg","批量删除成功");
            }else{
                msg.put("msg","批量删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    /**
     * 批量发布url
     * @param ids
     * @return
     */
    @RequestMapping("/releaseManyUrl/{ids}")
    @ResponseBody
    @Transactional
    public Map<String, String> releaseManyUrl(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            int i=bannedUrlService.releaseMany(idss);
            if(i==0){
                msg.put("msg","批量发布,状态："+msgTransform(i));
            }else{
                msg.put("msg","批量发布,状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    /**
     * 发布单条url信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/urlRelease/{id}")
    @Transactional
    public String urlRelease(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            int i=bannedUrlService.releaseMany(ids);
            if(i==0){
                model.addFlashAttribute("loadmsg", "发布状态:"+msgTransform(i));
            }else{
                model.addFlashAttribute("loadmsg", "发布状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }

        return "redirect:/measures/URL#_4";
    }

    /* 禁止URL end*/
   /* 游戏屏蔽库 begin*/

    /**
     * 进入游戏屏蔽库列表页面
     * @return
     */
    @RequestMapping("/game")
    public String game(){
        return "measures/measuresGame";
    }

    /**
     * 异步调用json，返回程序（游戏）列表数据
     * @param bannedProgBO
     * @return
     */
    @RequestMapping("/bannedprogjson")
    @ResponseBody
    public String getBannedProgList(@ModelAttribute BannedProgBO bannedProgBO){
        String json = null;
        try{
            PageInfo pr =bannedProgService.getAllBannedProg(bannedProgBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入程序（游戏）添加页面
     * @return
     */
    @RequestMapping("/intoGameAdd")
    public String intoGameAdd(){

        return "measures/measuresGameAdd";
    }

    /**
     * 提交程序（游戏）添加数据
     * @param bannedProgBO
     * @param model
     * @return
     */
    @RequestMapping("/submitGameAdd")
    public String submitGameAdd(@ModelAttribute BannedProgBO bannedProgBO,RedirectAttributes model){
        if(bannedProgService.save(bannedProgBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
       }else{
           model.addFlashAttribute("loadmsg", "保存失败");
       }
        return "redirect:/measures/game#_4";
    }

    /**
     * 进入程序(游戏)修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoGameEdit/{id}")
    public String intoGameEdit(@PathVariable Integer id,Model model){
        BannedProgBO bannedProg=bannedProgService.getBannedProg(id);
        model.addAttribute("bannedProg",bannedProg);
        return "measures/measuresGameEdit";
    }

    /**
     * 提交程序(游戏)修改信息
     * @param bannedProgBO
     * @param model
     * @return
     */
    @RequestMapping("/submitGameEdit")
    public String submitGameEdit(@ModelAttribute BannedProgBO bannedProgBO,RedirectAttributes model){
        bannedProgBO.setIsPub(0);
        if(bannedProgService.update(bannedProgBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/game#_4";
    }

    /**
     * 删除单条程序（游戏）信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/deleteGame/{id}")
    public String gameDelete(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            if(bannedProgService.deleteMany(ids)){
                model.addFlashAttribute("loadmsg", "删除成功");
            }else{
                model.addFlashAttribute("loadmsg", "删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");
        }

        return "redirect:/measures/game#_4";
    }

    /**
     * 批量删除程序(游戏)信息
     * @param ids
     * @return
     */
    @RequestMapping("/deleteManyGame/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyGame(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            if(bannedProgService.deleteMany(idss)){
                msg.put("msg","批量删除成功");
            }else{
                msg.put("msg","批量删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");
        }
        return msg;
    }

    /**
     * 批量发布程序（游戏）信息
     * @param ids
     * @return
     */
    @RequestMapping("/releaseManyGame/{ids}")
    @ResponseBody
    @Transactional
    public Map<String, String> releaseManyGame(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            int i=bannedProgService.releaseMany(idss);
            if(i==0){
                msg.put("msg","批量发布,状态"+msgTransform(i));
            }else{
                msg.put("msg","批量发布,状态："+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    /**
     * 发布单条程序（游戏）信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/gameRelease/{id}")
    @Transactional
    public String gameRelease(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            int i=bannedProgService.releaseMany(ids);
            if(i==0){
                model.addFlashAttribute("loadmsg", "发布状态:"+msgTransform(i));
            }else{
                model.addFlashAttribute("loadmsg", "发布状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }

        return "redirect:/measures/game#_4";
    }

    /* 游戏屏蔽库 end*/
    /* IP屏蔽库 begin*/

    /**
     * 进入ip屏蔽库列表页面
     * @param bannedUrlBO
     * @return
     */
    @RequestMapping("/IP")
    public String ip(@ModelAttribute BannedUrlBO bannedUrlBO){
        return "measures/measuresIp";
    }

    /**
     *异步调用json，返回禁止ip列表数据
     * @param bannedUrlBO
     * @return
     */
    @RequestMapping("/bannedipjson")
    @ResponseBody
    public String getBannedIpList(@ModelAttribute BannedUrlBO bannedUrlBO){
        String json = null;
        try{
            PageInfo pr = bannedUrlService.getBannedUrlsByUrlType(bannedUrlBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    /**
     * 进入ip修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/intoIpEdit/{id}")
    public String intoIpEdit(@PathVariable Integer id, Model model){
        BannedUrlBO bannedUrlBO=bannedUrlService.getBannedUrl(id);
        List<Monitor> monitors=monitorService.getPlaceMonitors();
        model.addAttribute("bannedUrl",bannedUrlBO);
        model.addAttribute("monitors",monitors);
        return "measures/measuresIpEdit";
    }

    /**
     * 进入ip添加页面
     * @param model
     * @return
     */
    @RequestMapping("/intoIpAdd")
    public String intoIpAdd(Model model){
        List<Monitor> monitors=monitorService.getPlaceMonitors();
        model.addAttribute("monitors",monitors);
        return "measures/measuresIpAdd";
    }

    /**
     * 提交ip添加信息，并返回ip列表页面
     * @param bannedUrlBO
     * @param model
     * @return
     */
    @RequestMapping("/submitIpAdd")
    public String submitIpAdd(@ModelAttribute BannedUrlBO bannedUrlBO,RedirectAttributes model){
        if(bannedUrlService.save(bannedUrlBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/measures/IP#_4";
    }

    /**
     * 提交ip修改信息，并返回ip列表页面
     * @param bannedUrlBO
     * @param model
     * @return
     */
    @RequestMapping("/submitIpEdit")
    public String submitIpEdit(@ModelAttribute BannedUrlBO bannedUrlBO,RedirectAttributes model){
        bannedUrlBO.setIsPub(0);
        if(bannedUrlService.update(bannedUrlBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/IP#_4";
    }

    /**
     * 删除单条ip信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/ipDelete/{id}")
    public String ipDelete(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            if(bannedUrlService.deleteMany(ids)){
                model.addFlashAttribute("loadmsg", "删除成功");
            }else{
                model.addFlashAttribute("loadmsg", "删除失败");

            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }
        return "redirect:/measures/IP#_4";
    }

    /**
     * 删除多条ip信息
     * @param ids
     * @return
     */
    @RequestMapping("/deleteManyIp/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyIp(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }        if(bannedUrlService.deleteMany(idss)){
                msg.put("msg","批量删除成功");
            }else{
                msg.put("msg","批量删除失败");

            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    @RequestMapping("/releaseManyIp/{ids}")
    @ResponseBody
    @Transactional
    public Map<String, String> releaseManyIp(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            int i=bannedUrlService.releaseMany(idss);
            if(i==0){
                msg.put("msg","批量发布,状态:"+msgTransform(i));
            }else{
                msg.put("msg","批量发布,状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    @RequestMapping("/ipRelease/{id}")
    @Transactional
    public String ipRelease(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            int i=bannedUrlService.releaseMany(ids);
            if(i==0){
                model.addFlashAttribute("loadmsg", "发布,状态:"+msgTransform(i));
            }else{
                model.addFlashAttribute("loadmsg", "发布,状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }

        return "redirect:/measures/IP#_4";
    }

    /* IP屏蔽库 end*/
    /* 特殊人员屏蔽库 begin*/
    @RequestMapping("/special")
    public String special(@ModelAttribute SpecialCustomerBO specialCustomerBO){
        return "measures/measuresSpecial";
    }

    @RequestMapping("/bannedspecialjson")
    @ResponseBody
    public String getBannedSpecialList(@ModelAttribute SpecialCustomerBO specialCustomerBO){
        String json = null;
        try{
            PageInfo pr = specialCustomerService.getAllSpecialCustomer(specialCustomerBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/intoSpecialEdit/{id}")
    public String intoSpecialEdit(@PathVariable Integer id, Model model){
        SpecialCustomerBO specialCustomerBO=specialCustomerService.getSpecialCustomer(id);
        model.addAttribute("specialCustomer",specialCustomerBO);
        return "measures/measuresSpecialEdit";
    }

    @RequestMapping("/intoSpecialAdd")
    public String intoSpecialAdd(){
        return "measures/measuresSpecialAdd";
    }


    @RequestMapping("/submitSpecialAdd")
    public String submitSpecialAdd(@ModelAttribute SpecialCustomerBO specialCustomerBO,RedirectAttributes model){
        if(specialCustomerService.save(specialCustomerBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/measures/special#_4";
    }


    @RequestMapping("/submitSpecialEdit")
    public String submitSpecialEdit(@ModelAttribute SpecialCustomerBO specialCustomerBO,RedirectAttributes model){
        specialCustomerBO.setIsPub(0);
        if(specialCustomerService.update(specialCustomerBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/special#_4";
    }

    @RequestMapping("/specialDelete/{id}")
    public String specialDelete(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            if(specialCustomerService.deleteMany(ids)){
                model.addFlashAttribute("loadmsg", "删除成功");
            }else{
                model.addFlashAttribute("loadmsg", "删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }

        return "redirect:/measures/special#_4";
    }

    @RequestMapping("/deleteManySpecial/{ids}")
    @ResponseBody
    public Map<String, String> deleteManySpecial(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            if(specialCustomerService.deleteMany(idss)){
                msg.put("msg","批量删除成功");
            }else{
                msg.put("msg","批量删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

        return msg;
    }

    @RequestMapping("/releaseManySpecial/{ids}")
    @ResponseBody
    @Transactional
    public Map<String, String> releaseManySpecial(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            int i=specialCustomerService.releaseMany(idss);
            if(i==0){
                msg.put("msg","批量发布,状态："+msgTransform(i));
            }else{
                msg.put("msg","批量发布,状态:"+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }


        return msg;
    }

    @RequestMapping("/specialRelease/{id}")
    @Transactional
    public String specialRelease(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            int i=specialCustomerService.releaseMany(ids);
            if(i==0){
                model.addFlashAttribute("loadmsg", "发布状态："+msgTransform(i));
            }else{
                model.addFlashAttribute("loadmsg", "发布状态："+msgTransform(1));
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");
        }

        return "redirect:/measures/special#_4";
    }
    /* 特殊人员屏蔽库 begin*/
     /* 关键字库 begin*/
    @RequestMapping("/keyword")
    public String keyword(@ModelAttribute KeyWordBO keyWordBO){
        return "measures/measuresKeyWord";
    }

    @RequestMapping("/keywordjson")
    @ResponseBody
    public String getKeyWordList(@ModelAttribute KeyWordBO keyWordBO){
        String json = null;
        try{
            PageInfo pr = keyWordService.getAllKeyWord(keyWordBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/intoKeyWordEdit/{id}")
    public String intoKeyWordEdit(@PathVariable Integer id, Model model){
        KeyWordBO keyWordBO=keyWordService.getKeyWord(id);
        model.addAttribute("keyWordBO",keyWordBO);
        return "measures/measuresKeyWordEdit";
    }

    @RequestMapping("/intoKeyWordAdd")
    public String intoKeyWordAdd(){
        return "measures/measuresKeyWordAdd";
    }


    @RequestMapping("/submitKeyWordAdd")
    public String submitKeyWordAdd(@ModelAttribute KeyWordBO keyWordBO,RedirectAttributes model){
        if(keyWordService.save(keyWordBO)){
            model.addFlashAttribute("loadmsg", "保存成功");
        }else{
            model.addFlashAttribute("loadmsg", "保存失败");
        }
        return "redirect:/measures/keyword#_4";
    }


    @RequestMapping("/submitKeyWordEdit")
    public String submitKeyWordEdit(@ModelAttribute KeyWordBO keyWordBO,RedirectAttributes model){
        keyWordBO.setIsPub(0);
        if(keyWordService.update(keyWordBO)){
            model.addFlashAttribute("loadmsg", "修改成功");
        }else{
            model.addFlashAttribute("loadmsg", "修改失败");
        }
        return "redirect:/measures/keyword#_4";
    }

    @RequestMapping("/keywordDelete/{id}")
    public String keywordDelete(@PathVariable Integer id,RedirectAttributes model){
        try{
            Integer[] ids={id};
            if(keyWordService.deleteMany(ids)){
                model.addFlashAttribute("loadmsg", "删除成功");
            }else{
                model.addFlashAttribute("loadmsg", "删除失败");
            }        }catch (Exception e){
            e.printStackTrace();
            model.addFlashAttribute("loadmsg", "网络连接异常");

        }

        return "redirect:/measures/keyword#_4";
    }

    @RequestMapping("/deleteManyKeyWord/{ids}")
    @ResponseBody
    public Map<String, String> deleteManyKeyWord(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            if(keyWordService.deleteMany(idss)){
                msg.put("msg","批量删除成功");
            }else{
                msg.put("msg","批量删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");
        }

        return msg;
    }

    @RequestMapping("/releaseManyKeyWord/{ids}")
    @ResponseBody
    public Map<String, String> releaseManyKeyWord(@PathVariable String ids){
        Map<String,String> msg=new HashMap<>();
        try{
            String[] sid=ids.split(",");
            Integer[] idss=new Integer[sid.length];
            for(int i=0;i<idss.length;i++){
                idss[i]=Integer.parseInt(sid[i]);
            }
            int i=keyWordService.releaseMany(idss);
            if(i==0){
                msg.put("msg","批量发布,状态:"+msgTransform(i));
            }else{
                msg.put("msg","批量发布,状态:"+msgTransform(1));

            }
        }catch (Exception e){
            e.printStackTrace();
            msg.put("msg","网络连接异常");

        }

          return msg;
    }

    @RequestMapping("/keywordRelease/{id}")
    public String keywordRelease(@PathVariable Integer id,RedirectAttributes model){
    try{
        Integer[] ids={id};
        int i=keyWordService.releaseMany(ids);
        if(i==0){
            model.addFlashAttribute("loadmsg", "发布状态:"+msgTransform(i));
        }else{
            model.addFlashAttribute("loadmsg", "发布状态"+msgTransform(1));
        }
    }catch (Exception e){
        e.printStackTrace();
        model.addFlashAttribute("loadmsg", "网络连接异常");

    }

        return "redirect:/measures/keyword#_4";
    }
    /* 关键字库 end*/


    @RequestMapping("/alert")
    public String alert(){
        return "measures/measuresAlert";
    }

    @RequestMapping("/intoAlertEdit")
    public String intoAlertEdit(){
        return "measures/measuresAlertEdit";
    }

    @RequestMapping("/submitAlertEdit")
    public String submitAlertEdit(){
        return "redirect:/measures/alert#_4";
    }

    @RequestMapping("/trustURL")
    public String trustUrl(){
        return "measures/measuresTrustUrl";
    }

    @RequestMapping("/intoTrustUrlAdd")
    public String intoTrustUrlAdd(){
        return "measures/measuresTrustUrlAdd";
    }

    @RequestMapping("/submitTrustUrlAdd")
    public String submitTrustUrlAdd(){
        return "redirect:/measures/trustURL#_4";
    }

    @RequestMapping("/intoTrustUrlEdit")
    public String intoTrustUrlEdit(){
        return "measures/measuresTrustUrlEdit";
    }

    @RequestMapping("/submitTrustUrlEdit")
    public String submitTrustUrlEdit(){
        return "redirect:/measures/trustURL#_4";
    }

    @RequestMapping("/trustUrlRemove")
    public String trustURLRemove(){
        return "redirect:/measures/trustURL#_4";
    }

    @RequestMapping("/public")
    public String publicbutton(){
        return "measures/measuresPlace1";
    }

    private String msgTransform(int state){
        String msg;
        switch (state){
            case 0:msg="成功";break;
            case 1:msg="失败";break;
            case 2:msg="消息有误";break;
            case 3:msg="不支持";break;
            case 4:msg="处理确认";break;
            default:msg="-1";
        }
        return msg;

    }
}
