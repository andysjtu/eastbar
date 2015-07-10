/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.report;

import net.sf.jasperreports.engine.JRDataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.eastbar.web.CreateTimeList;
import org.eastbar.web.Month;
import org.eastbar.web.PR2Json;
import org.eastbar.web.PageInfo;
import org.eastbar.web.ipc.MonitorService;
import org.eastbar.web.ipc.SiteService;
import org.eastbar.web.ipc.biz.MonitorBO;
import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.report.biz.*;
import org.eastbar.web.report.dao.MockDataFactory;
import org.eastbar.web.report.entity.StatDaySiteOper;
import org.eastbar.web.report.entity.StatMonthSiteOper;
import org.eastbar.web.report.entity.StatYearSiteOper;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.eastbar.web.unit.SitePunishService;
import org.eastbar.web.unit.biz.SitePunishBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author C.Lins@aliyun.com
 * @date 2014年09月02
 * @time 上午11:48
 * @description :
 */
@Controller
@RequestMapping(value="/report")
public class ReportController {

    @Autowired
    private StatYearAlarmService statYearAlarmService;
    @Autowired
    private StatMonthAlarmService statMonthAlarmService;
    @Autowired
    private StatDayAlarmService statDayAlarmService;
    @Autowired
    private StatHourSiteOperService statHourSiteOperService;
    @Autowired
    private StatDaySiteOperService statDaySiteOperService;
    @Autowired
    private StatMonthSiteOperService statMonthSiteOperService;
    @Autowired
    private StatYearSiteOperService statYearSiteOperService;

    @Autowired
    private StatYearUrlService statYearUrlService;
    @Autowired
    private StatMonthUrlService statMonthUrlService;
    @Autowired
    private StatDayUrlService statDayUrlService;

    @Autowired
    private StatDayProgService statDayProgService;
    @Autowired
    private StatMonthProgService statMonthProgService;
    @Autowired
    private StatYearProgService statYearProgService;


    @Autowired
    private MonitorService monitorService;

	@Autowired
	private SitePunishService sitePunishService;
	@Autowired
	private SiteService siteService;

    private StatHourSiteOperBO statHourSiteOperBO;

    @RequestMapping("/list")
    public String list(){
        return "report/reportList";
    }

    @RequestMapping("/history")
    public String history(@ModelAttribute SearchBO searchBO,Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        List<String> years= CreateTimeList.createYearList();
        List<Month> months=CreateTimeList.createMonthList();
        model.addAttribute("months",months);
        model.addAttribute("monitorList",monitorList);
        model.addAttribute("years",years);
        return "report/reportHistory";
    }

    @RequestMapping("/historySearch")
    public String historySearch(@ModelAttribute SearchBO searchBO,HttpSession session){
        MonitorBO monitorBo=new MonitorBO();
        if("d".equals(searchBO.getObjectType())){
            if(ShiroCustomUtils.getMonitors().size()>0){
                String monitorCode=ShiroCustomUtils.getMonitors().get(0);
                monitorBo=monitorService.get(monitorCode);
                searchBO.setObjectView(monitorBo.getName());
                searchBO.setCodeView(monitorCode);
            }else{
                searchBO.setObjectView("上海");
                searchBO.setCodeView("310");
            }
        }else if("s".equals(searchBO.getObjectType())){
            monitorBo=monitorService.get(searchBO.getFmonitorCode());
            searchBO.setObjectView(monitorBo.getName());
            searchBO.setCodeView(searchBO.getFmonitorCode());
        }else if("q".equals(searchBO.getObjectType())){
            monitorBo=monitorService.get(searchBO.getSmonitorCode());
            searchBO.setObjectView(monitorBo.getName());
            searchBO.setCodeView(searchBO.getFmonitorCode());
        }else if("c".equals(searchBO.getObjectType())){
            SiteBO siteBO=siteService.get(searchBO.getSiteCode());
            searchBO.setObjectView(siteBO.getName());
            searchBO.setCodeView(siteBO.getSiteCode());
        }
        session.setAttribute("searchBO",searchBO);
        return "report/reportHistoryResult";
    }

    @RequestMapping("/historyjson")
    @ResponseBody
    public String historyJson(@ModelAttribute StatHourSiteOperBO statHourSiteOperBO,HttpSession session){
        String json=null;
        PageInfo pr=null;
        try{
           SearchBO searchBO= (SearchBO)session.getAttribute("searchBO");
            List<String> staTime=new ArrayList<>();
            List<String> customerCount=new ArrayList<>();
            List<String> terminaAveage=new ArrayList<>();
            List<String> terminaTime=new ArrayList<>();
            statHourSiteOperBO.setType(searchBO.getType());
            //如果选择的是按当前系统
            if(searchBO.getObjectType()!=null&&"d".equals(searchBO.getObjectType())){
                List<String> monitorCodes= ShiroCustomUtils.getMonitors();
                if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                    statHourSiteOperBO.setMonitorCode(monitorCodes.get(0));
                }
            //如果选择的是按省/市级
            }else if(searchBO.getObjectType()!=null&&"s".equals(searchBO.getObjectType())){
                statHourSiteOperBO.setMonitorCode(searchBO.getFmonitorCode());
            //如果选择的是按区/县级
            }else if(searchBO.getObjectType()!=null&&"q".equals(searchBO.getObjectType())){
                statHourSiteOperBO.setMonitorCode(searchBO.getSmonitorCode());
            //如果选择的是按场所
            }else{
                statHourSiteOperBO.setSiteCode(searchBO.getSiteCode());
            }
            if(searchBO.getType()!=null){
                //选择时间:按年
                if("y".equals(searchBO.getType())){
                    statHourSiteOperBO.setStartYear(searchBO.getByear());
                    statHourSiteOperBO.setEndYear(searchBO.getEyear());
                    StatYearSiteOperBO statYearSiteOperBO=new StatYearSiteOperBO();
                    BeanUtils.copyProperties(statYearSiteOperBO,statHourSiteOperBO);
                    pr=statYearSiteOperService.getAll(statYearSiteOperBO);
                    //从pr中抽出list，然后拆解list组合成图标所需要的json
                    List<StatYearSiteOper> statYearSiteOperBOs=pr.getListing();
                    for(int i=0;i<statYearSiteOperBOs.size();i++){
                        if(!"".equals(statYearSiteOperBOs.get(i).getSiteCode()) && statYearSiteOperBOs.get(i).getSiteCode()!=null){
                            staTime.add(i,statYearSiteOperBOs.get(i).getSiteCode()+"-"+statYearSiteOperBOs.get(i).getStaYear()+"年");
                        }else{
                            staTime.add(i,statYearSiteOperBOs.get(i).getMonitorCode()+"-"+statYearSiteOperBOs.get(i).getStaYear()+"年");

                        }
                        customerCount.add(i,statYearSiteOperBOs.get(i).getCustomerCount()+"");
                        //下面两条待修改
                        terminaAveage.add(i,"378");
                        terminaTime.add(i,"411");

                    }
                //选择时间:按月
                }else if("m".equals(searchBO.getType())){
                    String btime=searchBO.getBmyear().toString()+searchBO.getBmonth().toString();
                    String etime=searchBO.getEmyear().toString()+searchBO.getEmonth().toString();
                    statHourSiteOperBO.setBtime(btime);
                    statHourSiteOperBO.setEtime(etime);
                    StatMonthSiteOperBO statMonthSiteOperBO=new StatMonthSiteOperBO();
                    BeanUtils.copyProperties(statMonthSiteOperBO,statHourSiteOperBO);
                    pr=statMonthSiteOperService.getAll(statMonthSiteOperBO);
                    //从pr中抽出list，然后拆解list组合成图标所需要的json
                    List<StatMonthSiteOper> statMonthSiteOperBOs=pr.getListing();
                    for(int i=0;i<statMonthSiteOperBOs.size();i++){
                        if(!"".equals(statMonthSiteOperBOs.get(i).getSiteCode()) && statMonthSiteOperBOs.get(i).getSiteCode()!=null){
                            staTime.add(i,statMonthSiteOperBOs.get(i).getSiteCode()+"-"+statMonthSiteOperBOs.get(i).getStaMonth()+"月");
                        }else{
                            staTime.add(i,statMonthSiteOperBOs.get(i).getMonitorCode()+"-"+statMonthSiteOperBOs.get(i).getStaMonth()+"月");
                        }
                        customerCount.add(i,statMonthSiteOperBOs.get(i).getCustomerCount()+"");
                        //下面两条待修改
                        terminaAveage.add(i,"378");
                        terminaTime.add(i,"411");
                    }
                //选择时间:按日
                }else{
                    statHourSiteOperBO.setBtime(searchBO.getBtime());
                    statHourSiteOperBO.setEtime(searchBO.getEtime());
                    StatDaySiteOperBO statDaySiteOperBO=new StatDaySiteOperBO();
                    BeanUtils.copyProperties(statDaySiteOperBO,statHourSiteOperBO);
                    pr=statDaySiteOperService.getAll(statDaySiteOperBO);
                    //从pr中抽出list，然后拆解list组合成图标所需要的json
                    List<StatDaySiteOper> statDaySiteOPerBOs=pr.getListing();
                    for(int i=0;i<statDaySiteOPerBOs.size();i++){
                        staTime.add(i,statDaySiteOPerBOs.get(i).getStaTime().substring(0,9));
                        customerCount.add(i,statDaySiteOPerBOs.get(i).getCustomerCount()+"");
                        //下面两条待修改
                        terminaAveage.add(i,"378");
                        terminaTime.add(i,"411");

                    }
                }
            }
            session.setAttribute("staTime", PR2Json.go(staTime));
            session.setAttribute("customerCount",PR2Json.go(customerCount));
            session.setAttribute("terminaAveage",PR2Json.go(terminaAveage));
            session.setAttribute("terminaTime",PR2Json.go(terminaTime));
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/gridjson")
    @ResponseBody
    public String gridJson(HttpSession session){
        String staTime=(String)session.getAttribute("staTime");
        String customerCount=(String)session.getAttribute("customerCount");
        String terminaAveage=(String)session.getAttribute("terminaAveage");
        String terminaTime=(String)session.getAttribute("terminaTime");
        String json="{\"staTime\":"+staTime+",\"customerCount\":"+customerCount+",\"terminaAveage\":"+terminaAveage+",\"terminaTime\":"+terminaTime+"}";
        System.out.println(json);
        return json;
    }

    @RequestMapping("/period")
    public String period(@ModelAttribute SearchBO searchBO,Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        model.addAttribute("monitorList",monitorList);
		List<Monitor> areaList = monitorService.getArea();
		model.addAttribute("areaList",areaList);
        return "report/reportPeriod";
    }

    @RequestMapping("/periodSearch")
    public String periodSearch(@ModelAttribute StatHourSiteOperBO statHourSiteOperBO,Model model,HttpServletRequest request){
		//如果选择的是按当前系统
		if("d".equals(request.getParameter("objectType"))){
			List<String> monitorCodes= ShiroCustomUtils.getMonitors();
			if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
				statHourSiteOperBO.setMonitorCode(monitorCodes.get(0));
			}
		//如果选择的是按省/市级
		}else if("s".equals(request.getParameter("objectType"))){
			statHourSiteOperBO.setMonitorCode(request.getParameter("fmonitorCode"));
		//如果选择的是按区/县级
		}else if("q".equals(request.getParameter("objectType"))){
			statHourSiteOperBO.setMonitorCode(request.getParameter("smonitorCode"));
		//如果选择的是按场所
		}else if("c".equals(request.getParameter("objectType"))){
			statHourSiteOperBO.setSiteCode(request.getParameter("siteCode"));
		}

		model.addAttribute("beginTime",statHourSiteOperBO.getBtime().substring(0,10));
		model.addAttribute("endTime",statHourSiteOperBO.getEtime().substring(0,10));
		model.addAttribute("gogalType",request.getParameter("gogalType"));

		if("day".equals(request.getParameter("timeType"))){
			List lists=statHourSiteOperService.getListByDay(statHourSiteOperBO);
			//补全24小时  没有的 customer_count用0表示
			int[] res = new int[lists.size()];
			for(int i = 0; i < lists.size(); i++){
				Map map = (Map)lists.get(i);
				res[i] = (int)map.get("sta_hour");
			}
			int[] all ={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};
			//排序
			Arrays.sort(res);
			Arrays.sort(all);
			//补全
			for(int i=0;i<all.length;i++){
				int index = Arrays.binarySearch(res,all[i]);
				if(index < 0){
					Map<String,Integer> map = new HashMap<>();
					map.put("sta_hour",all[i]);
					map.put("customer_count",0);
					map.put("online_time",0);

					lists.add(map);
				}
			}
			//根据sta_hour进行排序（升序）
			Collections.sort(lists, new Comparator<Map<String,Object>>(){
				public int compare(Map<String,Object> o1,Map<String,Object> o2){
					return  (Integer)o2.get("sta_hour")<(Integer)o1.get("sta_hour")?1:( (Integer)o2.get("sta_hour")==(Integer)o1.get("sta_hour")?0:-1);
				}
			});
			List<Integer> counts=new  ArrayList();
			Map map = new HashMap();
			if("customer_count".equals(request.getParameter("gogalType"))){
				//只取customer_count
				for(int i=0;i<lists.size();i++){
					map = (Map)lists.get(i);
					Integer count = Integer.parseInt(map.get("customer_count").toString());
					counts.add(i,count);
				}
			}else if("online_time".equals(request.getParameter("gogalType"))){
				//只取online_time
				for(int i=0;i<lists.size();i++){
					map = (Map)lists.get(i);
					Integer count = Math.round(Float.parseFloat(map.get("online_time").toString()));
					counts.add(i,count);
				}
			}

			model.addAttribute("lists",counts);
			return "report/reportPeriodResult";
		}else if("week".equals(request.getParameter("timeType"))){
			List lists=statHourSiteOperService.getListByWeek(statHourSiteOperBO);

			//补全周日到周六  没有的 customer_count用0表示
			int[] res = new int[lists.size()];
			for(int i = 0; i < lists.size(); i++){
				Map map = (Map)lists.get(i);
				res[i] = (int)map.get("week");
			}
			int[] all ={1,2,3,4,5,6,7};
			//排序
			Arrays.sort(res);
			Arrays.sort(all);
			//补全
			for(int i=0;i<all.length;i++){
				int index = Arrays.binarySearch(res,all[i]);
				if(index < 0){
					Map<String,Integer> map = new HashMap<>();
					map.put("week",all[i]);
					map.put("customer_count",0);
					map.put("online_time",0);

					lists.add(map);
				}
			}
			//根据sta_hour进行排序（升序）
			Collections.sort(lists, new Comparator<Map<String,Object>>(){
				public int compare(Map<String,Object> o1,Map<String,Object> o2){
					return  (Integer)o2.get("week")<(Integer)o1.get("week")?1:( (Integer)o2.get("week")==(Integer)o1.get("week")?0:-1);
				}
			});
			List<Integer> counts=new  ArrayList();
			Map map = new HashMap();
			if("customer_count".equals(request.getParameter("gogalType"))){
				//只取customer_count
				for(int i=0;i<lists.size();i++){
					map = (Map)lists.get(i);
					Integer count = Integer.parseInt(map.get("customer_count").toString());
					counts.add(i,count);
				}
			}else if("online_time".equals(request.getParameter("gogalType"))){
				//只取online_time
				for(int i=0;i<lists.size();i++){
					map = (Map)lists.get(i);
					Integer count = Math.round(Float.parseFloat(map.get("online_time").toString()));
					counts.add(i,count);
				}
			}
			model.addAttribute("lists",counts);
			return "report/reportWeekPeriodResult";
		}
        return null;
    }

    @RequestMapping("/alert")
    public String alert(@ModelAttribute SearchBO searchBO,Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        List<String> years= CreateTimeList.createYearList();
        List<Month> months=CreateTimeList.createMonthList();
        model.addAttribute("months",months);
        model.addAttribute("years",years);
        model.addAttribute("monitorList",monitorList);
        return "report/reportAlert";
    }

    @RequestMapping("/alertSearch")
    public String alertSearch(@ModelAttribute SearchBO searchBO,HttpSession session){
        session.setAttribute("searchBO",searchBO);
        return "report/reportAlertResult";
    }

    @RequestMapping("/alertjson")
    @ResponseBody
    public String alertjson(@ModelAttribute StatDayAlarmBO statDayAlarmBO,HttpSession session){
        String json=null;
        PageInfo pr=null;
        try{
            SearchBO searchBO= (SearchBO)session.getAttribute("searchBO");
            statDayAlarmBO.setType(searchBO.getType());
            //如果选择的是当前系统
            if(searchBO.getObjectType()!=null&&"d".equals(searchBO.getObjectType())){
                List<String> monitorCodes= ShiroCustomUtils.getMonitors();
                if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                    statDayAlarmBO.setMonitorCode(monitorCodes.get(0));
                }
            //如果选择的是按省/市级
            }else if(searchBO.getObjectType()!=null&&"s".equals(searchBO.getObjectType())){
                statDayAlarmBO.setMonitorCode(searchBO.getFmonitorCode());
            //如果选择的是按区/县级
            }else if(searchBO.getObjectType()!=null&&"q".equals(searchBO.getObjectType())){
                statDayAlarmBO.setMonitorCode(searchBO.getSmonitorCode());
            //如果选择的是按场所
            }else{
                statDayAlarmBO.setSiteCode(searchBO.getSiteCode());
            }
            if(searchBO.getType()!=null){
                //选择时间：按年
                if("y".equals(searchBO.getType())){
                    statDayAlarmBO.setStartYear(searchBO.getByear());
                    statDayAlarmBO.setEndYear(searchBO.getEyear());
                    StatYearAlarmBO statYearAlarmBO=new StatYearAlarmBO();
                    BeanUtils.copyProperties(statYearAlarmBO,statDayAlarmBO);
                    pr=statYearAlarmService.getAll(statYearAlarmBO);
                //选择时间：按月
                }else if("m".equals(searchBO.getType())){
                    String btime=searchBO.getBmyear().toString()+searchBO.getBmonth().toString();
                    String etime=searchBO.getEmyear().toString()+searchBO.getEmonth().toString();
                    statDayAlarmBO.setBtime(btime);
                    statDayAlarmBO.setEtime(etime);
                    StatMonthAlarmBO statMonthAlarmBO=new StatMonthAlarmBO();
                    BeanUtils.copyProperties(statMonthAlarmBO,statDayAlarmBO);
                    pr=statMonthAlarmService.getAll(statMonthAlarmBO);
                //选择时间：按日
                }else{
                    statDayAlarmBO.setBtime(searchBO.getBtime());
                    statDayAlarmBO.setEtime(searchBO.getEtime());
                    pr=statDayAlarmService.getAll(statDayAlarmBO);
                }
            }
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/punishSearch")
    public String punishSearch(Model model){
		model.addAttribute("siteList",siteService.getAll());
        return "report/reportPunish";
    }

	@RequestMapping("/punishSearchJson")
	public String punishSearchJson(@ModelAttribute SitePunishBO sitePunishBO,HttpServletRequest request,Model model){
		String str = request.getParameter("punishReasonOrType");
		if("处罚原因".equals(str)){
			List list = sitePunishService.getPunishReasonResult(sitePunishBO);
			int[] res = new int[list.size()];
			for(int i = 0; i < list.size(); i++){
				Map map = (Map)list.get(i);
				res[i] = (int)map.get("punish_reason");
			}
			int[] all ={1,2,3,4,5,6,7,8,9,10,11,12,13,14};

			Arrays.sort(res);
			Arrays.sort(all);

			for(int i=0;i<all.length;i++){
				int index = Arrays.binarySearch(res,all[i]);
				if(index < 0){
					Map<String,Integer> map = new HashMap<>();
					map.put("punish_reason",all[i]);
					map.put("total",0);

					list.add(map);
				}
			}

			//根据punish_reason进行排序（升序）
			Collections.sort(list, new Comparator<Map<String,Object>>(){
				public int compare(Map<String,Object> o1,Map<String,Object> o2){
					return  (Integer)o2.get("punish_reason")<(Integer)o1.get("punish_reason")?1:( (Integer)o2.get("punish_reason")==(Integer)o1.get("punish_reason")?0:-1);
				}
			});

			model.addAttribute("list", list);
			return "report/reportPunishReasonResult";
		}
		if("处罚类型".equals(str)){
			List list = sitePunishService.getPunishTypeResult(sitePunishBO);
			int[] res = new int[list.size()];
			for(int i = 0; i < list.size(); i++){
				Map map = (Map)list.get(i);
				res[i] = (int)map.get("punish_type");
			}
			int[] all ={1,2,3,4,5,6};

			Arrays.sort(res);
			Arrays.sort(all);

			for(int i=0;i<all.length;i++){
				int index = Arrays.binarySearch(res,all[i]);
				if(index < 0){
					Map<String,Integer> map = new HashMap<>();
					map.put("punish_type",all[i]);
					map.put("total",0);

					list.add(map);
				}
			}

			//根据punish_reason进行排序（升序）
			Collections.sort(list, new Comparator<Map<String,Object>>(){
				public int compare(Map<String,Object> o1,Map<String,Object> o2){
					return  (Integer)o2.get("punish_type")<(Integer)o1.get("punish_type")?1:( (Integer)o2.get("punish_type")==(Integer)o1.get("punish_type")?0:-1);
				}
			});
			model.addAttribute("list",list);
			return "report/reportPunishTypeResult";
		}
		return null;
	}

    @RequestMapping("/operateRank")
    public String operateRank(Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        List<String> years= CreateTimeList.createYearList();
        List<Month> months=CreateTimeList.createMonthList();
        model.addAttribute("months",months);
        model.addAttribute("years",years);
        model.addAttribute("monitorList",monitorList);
        return "report/reportOperateRank";
    }

    @RequestMapping("/operateRankSearch")
    public String operateRankSearch(@ModelAttribute SearchBO searchBO,HttpSession session){
        session.setAttribute("searchBO",searchBO);
        return "report/reportOperateRankResult";
    }

    @RequestMapping("/operateRankJson")
    @ResponseBody
    public String operateRankJson(@ModelAttribute StatHourSiteOperBO statHourSiteOperBO,HttpSession session){
        String json=null;
        PageInfo pr=null;
        try{
            SearchBO searchBO= (SearchBO)session.getAttribute("searchBO");
            statHourSiteOperBO.setType(searchBO.getType());
            //如果选择的是当前系统
            if(searchBO.getObjectType()!=null&&"d".equals(searchBO.getObjectType())){
                List<String> monitorCodes= ShiroCustomUtils.getMonitors();
                if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                    statHourSiteOperBO.setMonitorCode(monitorCodes.get(0));
                }
            //如果选择的是按省/市级
            }else if(searchBO.getObjectType()!=null&&"s".equals(searchBO.getObjectType())){
                statHourSiteOperBO.setMonitorCode(searchBO.getFmonitorCode());
            //如果选择的是按区/县级
            }else if(searchBO.getObjectType()!=null&&"q".equals(searchBO.getObjectType())){
                statHourSiteOperBO.setMonitorCode(searchBO.getSmonitorCode());
            //如果选择的是按场所
            }else{
                statHourSiteOperBO.setSiteCode(searchBO.getSiteCode());
            }
            if(searchBO.getType()!=null){
                //选择时间：按年
                if("y".equals(searchBO.getType())){
                    statHourSiteOperBO.setStartYear(searchBO.getByear());
                    statHourSiteOperBO.setEndYear(searchBO.getEyear());
                    StatYearSiteOperBO statYearSiteOperBO=new StatYearSiteOperBO();
                    BeanUtils.copyProperties(statYearSiteOperBO,statHourSiteOperBO);
                    statYearSiteOperBO.setOrder("desc");
                    pr=statYearSiteOperService.getAll(statYearSiteOperBO);
                //选择时间：按月
                }else if("m".equals(searchBO.getType())){
                    String btime=searchBO.getBmyear().toString()+searchBO.getBmonth().toString();
                    String etime=searchBO.getEmyear().toString()+searchBO.getEmonth().toString();
                    statHourSiteOperBO.setBtime(btime);
                    statHourSiteOperBO.setEtime(etime);
                    StatMonthSiteOperBO statMonthSiteOperBO=new StatMonthSiteOperBO();
                    BeanUtils.copyProperties(statMonthSiteOperBO,statHourSiteOperBO);
                    statMonthSiteOperBO.setOrder("desc");
                    pr=statMonthSiteOperService.getAll(statMonthSiteOperBO);
                //选择时间：按日
                }else{
                    statHourSiteOperBO.setBtime(searchBO.getBtime());
                    statHourSiteOperBO.setEtime(searchBO.getEtime());
                    StatDaySiteOperBO statDaySiteOperBO=new StatDaySiteOperBO();
                    BeanUtils.copyProperties(statDaySiteOperBO,statHourSiteOperBO);
                    statDaySiteOperBO.setOrder("desc");
                    pr=statDaySiteOperService.getAll(statDaySiteOperBO);
                }
            }
            json=PR2Json.go(pr);
		}catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/urlRank")
    public String urlRank(Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        List<String> years= CreateTimeList.createYearList();
        List<Month> months=CreateTimeList.createMonthList();
        model.addAttribute("months",months);
        model.addAttribute("monitorList",monitorList);
        model.addAttribute("years",years);
        return "report/reportUrlRank";
    }

    @RequestMapping("/urlRankSearch")
    public String urlRankSearch(@ModelAttribute SearchBO searchBO,HttpSession session){
        session.setAttribute("searchBO",searchBO);
        return "report/reportUrlRankResult";
    }

    @RequestMapping("/urljson")
    @ResponseBody
    public String urlJson(@ModelAttribute StatDayUrlBO statDayUrlBO,HttpSession session){
        String json=null;
        PageInfo pr=null;
        try{
            SearchBO searchBO= (SearchBO)session.getAttribute("searchBO");
            if(searchBO.getObjectType()!=null&&"d".equals(searchBO.getObjectType())){
                List<String> monitorCodes= ShiroCustomUtils.getMonitors();
                if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                    statDayUrlBO.setMonitorCode(monitorCodes.get(0));
                }
            }else if(searchBO.getObjectType()!=null&&"s".equals(searchBO.getObjectType())){
                statDayUrlBO.setMonitorCode(searchBO.getFmonitorCode());
            }else if(searchBO.getObjectType()!=null&&"q".equals(searchBO.getObjectType())){
                statDayUrlBO.setMonitorCode(searchBO.getSmonitorCode());
            }else{
                statDayUrlBO.setSiteCode(searchBO.getSiteCode());
            }
            if(searchBO.getType()!=null){
                //选择时间：按年
                if("y".equals(searchBO.getType())){
                    statDayUrlBO.setStartYear(searchBO.getByear());
                    statDayUrlBO.setEndYear(searchBO.getEyear());
                    StatYearUrlBO statYearUrlBO=new StatYearUrlBO();
                    BeanUtils.copyProperties(statYearUrlBO,statDayUrlBO);
                    pr=statYearUrlService.getAll(statYearUrlBO);
                    //选择时间：按月
                }else if("m".equals(searchBO.getType())){
                    String btime=searchBO.getBmyear().toString()+searchBO.getBmonth().toString();
                    String etime=searchBO.getEmyear().toString()+searchBO.getEmonth().toString();
                    statDayUrlBO.setBtime(btime);
                    statDayUrlBO.setEtime(etime);
                    StatMonthUrlBO statMonthUrlBO=new StatMonthUrlBO();
                    BeanUtils.copyProperties(statMonthUrlBO,statDayUrlBO);
                    pr=statMonthUrlService.getAll(statMonthUrlBO);
                    //选择时间：按日
                }else{
                    statDayUrlBO.setBtime(searchBO.getBtime());
                    statDayUrlBO.setEtime(searchBO.getEtime());
                    pr=statDayUrlService.getAll(statDayUrlBO);
                }
            }
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/programRank")
    public String programRank(Model model){
        List<Monitor> monitorList=monitorService.getMonitors();
        List<String> years= CreateTimeList.createYearList();
        List<Month> months=CreateTimeList.createMonthList();
        model.addAttribute("months",months);
        model.addAttribute("monitorList",monitorList);
        model.addAttribute("years",years);

        return "report/reportProgramRank";
    }

    @RequestMapping("/programRankSearch")
    public String programRankSearch(@ModelAttribute SearchBO searchBO,HttpSession session){
        session.setAttribute("searchBO",searchBO);
        if("y".equals(searchBO.getType())){
            session.setAttribute("Btime",searchBO.getByear());
            session.setAttribute("Etime",searchBO.getEyear());
        }else if("m".equals(searchBO.getType())){
            session.setAttribute("Btime",searchBO.getBmyear()+"-"+searchBO.getBmonth());
            session.setAttribute("Etime",searchBO.getEmyear()+"-"+searchBO.getEmonth());
        }else if("d".equals(searchBO.getType())){
            session.setAttribute("Btime",searchBO.getBtime().substring(0,10));
            session.setAttribute("Etime",searchBO.getEtime().substring(0,10));
        }
        return "report/reportProgramRankResult";
    }

    @RequestMapping("/progjson")
    @ResponseBody
    public String progJson(@ModelAttribute StatDayProgBO statDayProgBO,HttpSession session){
        String json=null;
        PageInfo pr=null;
        try{
            SearchBO searchBO= (SearchBO)session.getAttribute("searchBO");
            if(searchBO.getObjectType()!=null&&"d".equals(searchBO.getObjectType())){
                List<String> monitorCodes= ShiroCustomUtils.getMonitors();
                if(monitorCodes.size()>0){//判断是否为超级管理员，如果是超级管理员，则size为0
                    statDayProgBO.setMonitorCode(monitorCodes.get(0));
                }
            }else if(searchBO.getObjectType()!=null&&"s".equals(searchBO.getObjectType())){
                statDayProgBO.setMonitorCode(searchBO.getFmonitorCode());
            }else if(searchBO.getObjectType()!=null&&"q".equals(searchBO.getObjectType())){
                statDayProgBO.setMonitorCode(searchBO.getSmonitorCode());
            }else{
                statDayProgBO.setSiteCode(searchBO.getSiteCode());
            }
            if(searchBO.getType()!=null){
                //选择时间：按年
                if("y".equals(searchBO.getType())){
                    statDayProgBO.setStartYear(searchBO.getByear());
                    statDayProgBO.setEndYear(searchBO.getEyear());
                    StatYearProgBO statYearProgBO=new StatYearProgBO();
                    BeanUtils.copyProperties(statYearProgBO,statDayProgBO);
                    pr=statYearProgService.getAll(statYearProgBO);
                    //选择时间：按月
                }else if("m".equals(searchBO.getType())){
                    String btime=searchBO.getBmyear().toString()+searchBO.getBmonth().toString();
                    String etime=searchBO.getEmyear().toString()+searchBO.getEmonth().toString();
                    statDayProgBO.setBtime(btime);
                    statDayProgBO.setEtime(etime);
                    StatMonthProgBO statMonthProgBO=new StatMonthProgBO();
                    BeanUtils.copyProperties(statMonthProgBO,statDayProgBO);
                    pr=statMonthProgService.getAll(statMonthProgBO);
                    //选择时间：按日
                }else{
                    statDayProgBO.setBtime(searchBO.getBtime());
                    statDayProgBO.setEtime(searchBO.getEtime());
                    pr=statDayProgService.getAll(statDayProgBO);
                }
            }
            json=PR2Json.go(pr);
        }catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/trustUrlRank")
    public String trustUrlRank(){
        return "report/reportTrustUrlRank";
    }

    @RequestMapping("/trustUrlRankSearch")
    public String trustUrlRankSearch(){
        return "report/reportTrustUrlRankResult";
    }

    @RequestMapping("/statePlace")
    public String statePlace(Model model){
        List<Monitor> monitors=monitorService.getMonitors();
        model.addAttribute("monitors",monitors);
        return "report/reportStatePlace";
    }

	@RequestMapping("/statePlaceJson")
	@ResponseBody
	public String statePlaceJson(@ModelAttribute SiteBO siteBO){
		String json = null;
		try{
			PageInfo pr = siteService.getSiteOnLine(siteBO);
			json = PR2Json.go(pr);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return json;
	}

    @RequestMapping("/welcome")
    public String welcome(){
        return "report/welcome";
    }


    @RequestMapping(value = "/testpdfReport")
    public ModelAndView testSalesReportPDF(ModelAndView modelAndView)
    {

        MockDataFactory dataprovider = new MockDataFactory();
        JRDataSource categoryData  = dataprovider.getTestData();
        Map<String,Object> parameterMap = new HashMap<String,Object>();
       // JREmptyDataSource emptyData = new JREmptyDataSource();
      //  parameterMap.put("datasource", emptyData);
        parameterMap.put("testdatasource", categoryData);

        // pdfReport is the View of our application
        // This is declared inside the /WEB-INF/jasper-views.xml
        modelAndView = new ModelAndView("pdfTestReport", parameterMap);

        // Return the View and the Model combined
        return modelAndView;
    }

}
