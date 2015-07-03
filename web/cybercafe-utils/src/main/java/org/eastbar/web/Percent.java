/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author cindy-jia
 * @date 2015年06月05
 * @time 上午9:54
 * @description :
 */
public class Percent {

    public static String getPercent(int x,int total){
        String result="";//接受百分比的值
        NumberFormat formatter = new DecimalFormat("0.00");
        String xx = formatter.format(x);
        double x_double=x*1.0;
        double total_double=total*1.0;
        Double resultd=new Double(x_double/total_double);
        result=resultd.toString();
        return result;
    }

    public static void main(String[] args){

        System.out.println(getPercent(12,100));
    }

}
