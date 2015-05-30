package org.eastbar.codec;

import org.dozer.DozerBeanMapper;

/**
 * Created by AndySJTU on 2015/5/21.
 */
public class DozerUtil {
    private static DozerBeanMapper mapper = new DozerBeanMapper();

    public static void copyProperties(Object src, Object dest) {
        mapper.map(src, dest);
    }




}
