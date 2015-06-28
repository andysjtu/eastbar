/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.basis;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eastbar.center.statusMachine.HostEvent;
import org.eastbar.center.statusMachine.core.Count;
import org.eastbar.center.statusMachine.core.Event;
import org.eastbar.center.statusMachine.core.Offset;
import org.eastbar.center.statusMachine.core.StatusSnapshotFactory;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author C.lins@aliyun.com
 * @date 2015年04月14
 * @time 上午10:39
 * @description :
 */
public class Center implements Serializable {

    private int[] runStatus = {0,0,0,0};
    private AtomicInteger openSite = new AtomicInteger(0);
    private AtomicInteger totalSite = new AtomicInteger(0);
    private Map<String,City> cityMap = new ConcurrentHashMap<>();
    private Timestamp lastUpDate;

    private static Center center;
    public static Center getInstance(){
        if(center==null){
            center = StatusSnapshotFactory.getInstance().readImage();
            if(center==null){
                center = new Center();
            }
        }
        return center;
    }

    private void addCity(City city){
        cityMap.put(city.getCode(), city);
    }

    public City findCity(String code){
        if(cityMap.containsKey(code)){
            return cityMap.get(code);
        }
        return null;
    }

    public  void analysis(Event event){
            String cityCode = event.getSiteCode().substring(0,4);
            City city = findCity(cityCode);
            if(event instanceof HostEvent){
                if(city==null){
                    city = new City();
                    city.setCode(cityCode);
                    addCity(city);
                }
            }
            Offset offset = city.analysis(event);
            this.openSite.getAndAdd(offset.getOpen());
            this.totalSite.getAndAdd(offset.getTotal());
            synchronized (this.runStatus){
                Count.runStatus(this.runStatus, offset.getRun());
            }
            this.lastUpDate = new Timestamp(System.currentTimeMillis());

            StatusSnapshotFactory.getInstance().saveToFile(center);
    }

    public int[] getRunStatus() {
        return runStatus;
    }


    public Timestamp getLastUpDate() {
        return lastUpDate;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
