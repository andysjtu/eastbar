/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.statusMachine.core;

/**
 * @author C.lins@aliyun.com
 * @date 2015年04月15
 * @time 上午11:04
 * @description :
 */
public class Count {

    public static void runStatus(int[] runStatus,int[] offset){
        int[] x = runStatus;
        int[] y = offset;
        x[0]+=y[0];
        x[1]+=y[1];
        x[2]+=y[2];
        x[3]+=y[3];
    }

    public static int[] offset(int[] nowValue,int[] pastValue){
        int[] x = pastValue;
        int[] y = nowValue;
        return new int[]{y[0]-x[0],y[1]-x[1],y[2]-x[2],y[3]-x[3]};
    }

    public static void overlay(int[] siteOffset,int[] offset){
        int[] x = siteOffset;
        int[] y = offset;
        x[0]+=y[0];
        x[1]+=y[1];
        x[2]+=y[2];
        x[3]+=y[3];
    }
}
