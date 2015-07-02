/********************************************************************************
*                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
*                          Copyright © 2003-2014                               *
********************************************************************************/
package org.eastbar.web;

import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

/**
* @author C.lins@aliyun.com
* @date 2014年08月17
* @time 下午2:05
* @description :
*/
@DirtiesContext
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class RoleDaoTest extends SpringTransactionalTestCase{

//    @Autowired
//    private RoleDao roleDao;

    @Test
    public void getRole() throws Exception{
//        Role role = roleDao.get(1);
//        System.out.println(role);
//        System.out.println(role.loadShrioPermissions());
        System.out.println(System.currentTimeMillis());
    }
}
