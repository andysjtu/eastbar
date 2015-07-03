/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.unit;
import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.unit.biz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping(value="/unit")
public class UnitController {

    @Autowired
    private CustomerHistoryService customerHistoryService;

    @Autowired
    private AlarmHistoryService alarmHistoryService;

    @Autowired
    private UrlHistoryService urlHistoryService;

    @Autowired
    private ProgHistoryService progHistoryService;

    @Autowired
    private CustomerHostService customerHostService;

    @Autowired
    private SitePunishService sitePunishService;

    @Autowired
    private MailHistoryService mailHistoryService;

    @Autowired
    private InstantMessageHistoryService instantMessageHistoryService;
    //private ObjectMapper objectMapper = null;

    @RequestMapping("/list")
    public String auth(){
        return "unit/unitList";
    }

    @RequestMapping("/alert")
    public String alert(){
        return "unit/unitAlert";
    }

    @RequestMapping("/alertjson")
    @ResponseBody
    public String alertjson(@ModelAttribute AlarmHistoryBO alarmHistoryBO){
        String json = null;
        try{
                PageInfo pr = alarmHistoryService.getAllAlarmHistory(alarmHistoryBO);
                json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/alertList/{siteCode}")
    public String alertList(@ModelAttribute AlarmHistoryBO alarmHistoryBO,Model model){
        if("-1".equals(alarmHistoryBO.getSiteCode())){
            model.addAttribute("siteCode","");
        }else{
            model.addAttribute("siteCode",alarmHistoryBO.getSiteCode());
        }
        return "unit/unitAlertList";
    }


    @RequestMapping("/customer")
    public String customer(){
        return "unit/unitCustomer";
    }

    @RequestMapping("/customerjson")
    @ResponseBody
    public String customerjson(@ModelAttribute CustomerHostBO customerHostBO){

        String json = null;
        try{
            PageInfo pr = customerHostService.getAllCustomerHost(customerHostBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/customerList")
    public String customerList(@ModelAttribute CustomerHostBO customerHostBO){
        return "unit/unitCustomerList";
    }

    @RequestMapping("/log")
    public String log(){
        return "unit/unitLog";
    }

    @RequestMapping("/logjson")
    @ResponseBody
    public String logjson(@ModelAttribute CustomerHistoryBO customerHistoryBO){

        String json = null;
        try{
            PageInfo pr = customerHistoryService.getAllCustomerHistory(customerHistoryBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/logList")
    public String logList(@ModelAttribute CustomerHistoryBO customerHistoryBO){
        return "unit/unitLogList";
    }

    @RequestMapping("/url")
    public String url(){
        return "unit/unitUrl";
    }


    @RequestMapping("/urljson")
    @ResponseBody
    public String urljson(@ModelAttribute UrlHistoryBO urlHistoryBO){

        String json = null;
        try{
            PageInfo pr = urlHistoryService.getAllUrlHistory(urlHistoryBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;
    }


    @RequestMapping("/urlList")
    public String urlList(@ModelAttribute UrlHistoryBO urlHistoryBO){
        return "unit/unitUrlList";
    }

    @RequestMapping("/program")
    public String program(){
        return "unit/unitProgram";
    }

    @RequestMapping("/programjson")
    @ResponseBody
    public String programjson(@ModelAttribute ProgHistoryBO progHistoryBO){
        String json = null;
        try{
            PageInfo pr = progHistoryService.getAllProgHistory(progHistoryBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;

    }

    @RequestMapping("/sitePunish")
    public String sitePunish(@ModelAttribute SitePunishBO sitePunishBO){
        return "unit/unitPunishList";
    }


    @RequestMapping("/sitepunishjson")
    @ResponseBody
    public String sitePunishJson(@ModelAttribute SitePunishBO sitePunishBO){
        String json = null;
        try{
            PageInfo pr = sitePunishService.getAllSitePunish(sitePunishBO);
            json = PR2Json.go(pr);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return json;

    }

    @RequestMapping("/programList")
    public String programList(@ModelAttribute ProgHistoryBO progHistoryBO){
        return "unit/unitProgramList";
    }

    @RequestMapping("/mailList")
    public String mailList(@ModelAttribute MailHistoryBO mailHistoryBO){
        return "unit/unitMailList";
    }

    @RequestMapping("mailjson")
    @ResponseBody
    public String mailJson(@ModelAttribute MailHistoryBO mailHistoryBO){
        String json=null;
        try{
            PageInfo pr=mailHistoryService.getAll(mailHistoryBO);
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/instantList")
    public String instantList(@ModelAttribute InstantMessageHistoryBO instantMessageHistoryBO){
        return "unit/unitInstantList";
    }

    @RequestMapping("instantjson")
    @ResponseBody
    public String instantJson(@ModelAttribute InstantMessageHistoryBO instantMessageHistoryBO){
        String json=null;
        try{
            PageInfo pr=instantMessageHistoryService.getAll(instantMessageHistoryBO);
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/outPlace")
    public String outPlace(){
        return "unit/unitOutPlace";
    }

}
