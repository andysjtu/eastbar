/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月23
 * @time 上午10:30
 * @description :
 */
public class InitSearchTime {
    public static void init(String... times){
//        String btime = ShiroCustomUtils.getCurrentUserLastLogin();
        String etime = Times.now();

//        if(btime == "-1"){
            times[0] = Times.minusDays(etime, 30);
//        }else{
//            times[0] = btime;
//        }

        times[1] = Times.plusDays(etime, 1);
    }
}
