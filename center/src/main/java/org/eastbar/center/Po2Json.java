/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.center;

import org.springside.modules.mapper.JsonMapper;

/**
 * @author cindy-jia
 * @date 2015年05月08
 * @time 上午10:18
 * @description :
 */
public class Po2Json {


    private static JsonMapper binder = JsonMapper.nonDefaultMapper();

    /**
     * 把对象转成json格式的
     * @param o
     * @return
     */
    public static String toJson(Object o){
       StringBuilder sb=new StringBuilder();
       sb.append(binder.toJson(o));
        return sb.toString();
    }

}
