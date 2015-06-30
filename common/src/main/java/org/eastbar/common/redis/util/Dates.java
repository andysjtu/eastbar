/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.common.redis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cindy-jia
 * @date 2015年06月30
 * @time 上午10:55
 * @description :
 */
public class Dates {

    public static String dateTransfer(String date){
        String dateString="";
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date d = format.parse(date);
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateString = formatter.format(d);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateString;
    }
}
