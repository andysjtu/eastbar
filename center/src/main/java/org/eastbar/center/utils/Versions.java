/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center.utils;

/**
 * @author cindy-jia
 * @date 2014年11月05
 * @time 下午7:21
 * @description :
 */
public class Versions {

    public static String transString(String[] arry){

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < arry.length; i++){
            sb. append(arry[i]);
            if(i<arry.length-1){
                sb.append(".");
            }
        }
        return sb.toString();
    }

    public static String computeVersion(String version){
        String[] arry = version.split("\\.");
        String nums=arry[arry.length-1];
        Integer num=Integer.parseInt(nums);
        num=num+1;
        if(num<=1024){
            arry[arry.length-1]=num+"";
            return transString(arry);
        }else{
            nums=arry[arry.length-2];
            num=Integer.parseInt(nums);
            num=num+1;
            if(num<=1024){
                arry[arry.length-2]=num+"";
                return  transString(arry);
            }
            else{
                nums=arry[arry.length-3];
                num=Integer.parseInt(nums);
                num=num+1;
                arry[arry.length-3]=num+"";
                return transString(arry);
            }
        }

    }
}
