/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author C.lins@aliyun.com
 * @date 2014年10月21
 * @time 下午1:29
 * @description : 将JAVABO对象中值不为空的属性放入MAP中
 */
public class Bean2Map {
    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> trans(Object obj) throws Exception {

        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    if(value != null && value != ""){
//                        if(key.equals("btime") || key.equals("etime")){
//                            map.put(key,Times.t2long((String) value));
//                        }else{
                            map.put(key, value);
//                        }
                    }
                }
            }

        return map;

    }
}

