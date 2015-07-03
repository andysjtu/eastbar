/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cindy-jia
 * @date 2015年06月04
 * @time 上午9:37
 * @description :
 */
public class CreateTimeList {

    public static List<String> createYearList(){
        List list=new ArrayList();
        list.add("2003");
        list.add("2004");
        list.add("2005");
        list.add("2006");
        list.add("2007");
        list.add("2008");
        list.add("2009");
        list.add("2010");
        list.add("2011");
        list.add("2012");
        list.add("2013");
        list.add("2014");
        list.add("2015");
        list.add("2016");
        list.add("2017");
        list.add("2018");
        list.add("2019");
        list.add("2020");
        list.add("2021");
        list.add("2022");
        list.add("2023");
        list.add("2024");
        list.add("2025");
        list.add("2026");
        list.add("2027");
        list.add("2028");
        list.add("2029");
        list.add("2030");
        return list;
    }

    public static List<Month> createMonthList(){
        List<Month> list=new ArrayList();
        Month month=new Month();
        month.setValue("01");
        month.setShowValue("1");
        list.add(month);
        Month month2=new Month();
        month2.setValue("02");
        month2.setShowValue("2");
        list.add(month2);
        Month month3=new Month();
        month3.setValue("03");
        month3.setShowValue("3");
        list.add(month3);
        Month month4=new Month();
        month4.setValue("04");
        month4.setShowValue("4");
        list.add(month4);
        Month month5=new Month();
        month5.setValue("05");
        month5.setShowValue("5");
        list.add(month5);
        Month month6=new Month();
        month6.setValue("06");
        month6.setShowValue("6");
        list.add(month6);
        Month month7=new Month();
        month7.setValue("07");
        month7.setShowValue("7");
        list.add(month7);
        Month month8=new Month();
        month8.setValue("08");
        month8.setShowValue("8");
        list.add(month8);
        Month month9=new Month();
        month9.setValue("09");
        month9.setShowValue("9");
        list.add(month9);
        Month month10=new Month();
        month10.setValue("10");
        month10.setShowValue("10");
        list.add(month10);
        Month month11=new Month();
        month11.setValue("11");
        month11.setShowValue("11");
        list.add(month11);
        Month month12=new Month();
        month12.setValue("12");
        month12.setShowValue("12");
        list.add(month12);


        return list;
    }
}
