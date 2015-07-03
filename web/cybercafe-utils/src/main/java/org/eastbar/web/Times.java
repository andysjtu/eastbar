/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月23
 * @time 上午11:28
 * @description : 时间转换工具类
 */
public class Times {
    static DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    static DateTimeFormatter dtf2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");

    public static Long t2long(String time){
        DateTime dt = dtf2.parseDateTime(time);
        return dt.getMillis();
    }

    public static String l2time(Long time){
        if(time>0){
            DateTime dt = new DateTime(time);
            return dt.toString(dtf);
        }else{
            return "-1";
        }
    }


    public static String s2date(Date date){
        DateTime dt = new DateTime(date);
        return dt.toString(dtf);
    }

    public static String minusDays(Long time,int day){
        DateTime dt = new DateTime(time).minusDays(day);
        return dt.toString(dtf);
    }
    public static String minusDays(String time,int day){
        DateTime dt = dtf.parseDateTime(time).minusDays(day);
        return dt.toString(dtf);
    }

    public static String plusDays(Long time,int day){
        DateTime dt = new DateTime(time).plusDays(day);
        return dt.toString(dtf);
    }
    public static String plusDays(String time,int day){
        DateTime dt = dtf.parseDateTime(time).plusDays(day);
        return dt.toString(dtf);
    }

    public static String now(){
        return DateTime.now().toString(dtf);
    }

    public static Long nowLong(){
        return DateTime.now().getMillis();
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(Times.t2long("2013-10-23 11:05:26.0"));
        System.out.println(Times.l2time(0L));
        System.out.println(Times.s2date(new Date(Times.nowLong())));
        System.out.println(Times.minusDays(1382497527002L, 10));
        System.out.println(Times.now());


    }
}
