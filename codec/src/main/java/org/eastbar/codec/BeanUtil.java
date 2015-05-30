package org.eastbar.codec;

import org.dozer.DozerBeanMapper;

/**
 * Created by AndySJTU on 2015/5/20.
 */
public class BeanUtil {
    private static DozerBeanMapper beanMapper = new DozerBeanMapper();

    public static void copyBeanProperties(Object source,Object target){
        beanMapper.map(source,target);
    }
}
