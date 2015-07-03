/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import org.eastbar.web.ipc.biz.SiteBO;
import org.eastbar.web.ipc.entity.Monitor;
import org.eastbar.web.shiro.ShiroCustomUtils;
import org.springside.modules.mapper.JsonMapper;

import java.util.Collection;
import java.util.List;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月21
 * @time 下午4:28
 * @description :
 */
public class PR2Json {
    private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    public static String go(PageInfo pr){
        StringBuilder sb=new StringBuilder();

        sb.append("{\"total\":");
        sb.append(pr.getTotal());
        sb.append(",\"rows\":");
        sb.append(binder.toJson(pr.getListing())+"}");

        return sb.toString();
    }

    public static <T> String go(Collection<T> list){
        return binder.toJson(list);
    }

   //这个方法，格式对，但是没效果
    public static <T>String toFourTree(List<Monitor> monitorList,List<List<Monitor>> lists,List<List<SiteBO>> sites){
        StringBuilder sb=new StringBuilder();
        String id="";
        List<String> codes= ShiroCustomUtils.getMonitors();
        if(codes.size()>0){
            id=codes.get(0).trim();
        }else{
            id="310";
        }
        sb.append("[{\"id\":"+id);
        sb.append(",\"text\":\"全部\"");
        sb.append(",\"children\":[{");
        for(int s=0;s<monitorList.size();s++){
            Monitor monitor=monitorList.get(s);
            if(s>0)
                sb.append("},{");
            sb.append("\"id\":"+monitor.getMonitorCode());
            sb.append(",\"text\":\""+monitor.getName()+"\"");
            List<Monitor> monitors=lists.get(s);
            if(monitors.size()>0){
                sb.append(",\"children\":[{");
                for(int i=0;i<monitors.size();i++){
                    if(i>0)
                        sb.append("},{");
                    sb.append("\"id\":"+monitors.get(i).getMonitorCode());
                    sb.append(",\"text\":\""+monitors.get(i).getName()+"\"");
                    List<SiteBO> sites1=sites.get(i);
                    if(sites1.size()>0){
                        sb.append(",\"children\":[{");
                        for(int j=0;j<sites1.size();j++){
                            if(j>0)
                                sb.append("},{");
                            sb.append("\"id\":"+sites1.get(j).getSiteCode());
                            sb.append(",\"text\":\""+sites1.get(j).getName()+"\"");
                        }
                        sb.append("}]");
                    }
                }
                sb.append("}");
            }
        }
        sb.append("}]");
        sb.append("}]");
        return sb.toString();
    }

    public static <T>String to2Tree(List<Monitor> monitorList,List<List<Monitor>> lists){
        StringBuilder sb=new StringBuilder();

        sb.append("[{\"id\":1");
        sb.append(",\"text\":\"全部\"");
        sb.append(",\"children\":[{");
        Monitor monitor=monitorList.get(0);
        sb.append("\"id\":"+monitor.getMonitorCode());
        sb.append(",\"text\":\""+monitor.getName()+"\"");
        sb.append(",\"children\":[{");
        List<Monitor> monitors=lists.get(0);
        sb.append("\"id\":310101");
        sb.append(",\"text\":\"徐汇区网吧监管中心\"}");
        sb.append(",{\"id\":\"310102\"");
        sb.append(",\"text\":\"黄浦区网吧监管中心\"");
        sb.append("}]");
        sb.append("},{");
        monitor=monitorList.get(1);
        sb.append("\"id\":"+monitor.getMonitorCode());
        sb.append(",\"text\":\""+monitor.getName()+"\"");
        sb.append(",\"children\":[{");
        sb.append("\"id\":310201");
        sb.append(",\"text\":\"崇明县网吧监管中心\"");
        sb.append("}]");
        sb.append("}]");
        sb.append("}]");
        return sb.toString();
    }

    public static <T>String to3Tree(List<Monitor> monitorList,List<List<Monitor>> lists){
        StringBuilder sb=new StringBuilder();

        sb.append("[{\"id\":1");
        sb.append(",\"text\":\"全部\"");
        sb.append(",\"children\":[{");
        Monitor monitor=monitorList.get(0);
        sb.append("\"id\":"+monitor.getMonitorCode());
        sb.append(",\"text\":\""+monitor.getName()+"\"");
        sb.append(",\"children\":[{");
        List<Monitor> monitors=lists.get(0);
        for(int i=0;i<monitors.size();i++){
            if(i>0)
                sb.append(",{");
            sb.append("\"id\":"+monitors.get(i).getMonitorCode());
            sb.append(",\"text\":\""+monitors.get(i).getName()+"\"}");
        }
        sb.append("]");
        sb.append("},{");
        monitor=monitorList.get(1);
        sb.append("\"id\":"+monitor.getMonitorCode());
        sb.append(",\"text\":\""+monitor.getName()+"\"");
        sb.append(",\"children\":[{");
        monitors=lists.get(1);
        for(int i=0;i<monitors.size();i++){
            if(i>0)
                sb.append(",{");
            sb.append("\"id\":"+monitors.get(i).getMonitorCode());
            sb.append(",\"text\":\""+monitors.get(i).getName()+"\"}");
        }
        sb.append("]");
        sb.append("}]");
        sb.append("}]");
        return sb.toString();
    }

    //修改前
    public static <T>String toTree(List<Monitor> monitorList,List<List<Monitor>> lists){
        StringBuilder sb=new StringBuilder();
        String id="";
        List<String> codes=ShiroCustomUtils.getMonitors();
        if(codes.size()>0){
            id=codes.get(0).trim();
        }else{
            id="310";
        }
        sb.append("[{\"id\":"+id);
        sb.append(",\"text\":\"全部\"");
        sb.append(",\"children\":[{");
        for(int s=0;s<monitorList.size();s++){
            Monitor monitor=monitorList.get(s);
            if(s>0)
                sb.append("},{");
            sb.append("\"id\":"+monitor.getMonitorCode());
            sb.append(",\"text\":\""+monitor.getName()+"\"");
            List<Monitor> monitors=lists.get(s);
            if(monitors.size()>0){
                sb.append(",\"children\":[{");
                for(int i=0;i<monitors.size();i++){
                    if(i>0)
                        sb.append(",{");
                    sb.append("\"id\":"+monitors.get(i).getMonitorCode());
                    sb.append(",\"text\":\""+monitors.get(i).getName()+"\"}");
                }
                sb.append("]");
            }
        }
        sb.append("}]");
        sb.append("}]");
        return sb.toString();
    }


}
