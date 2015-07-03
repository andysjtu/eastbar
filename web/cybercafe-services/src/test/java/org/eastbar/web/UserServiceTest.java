/********************************************************************************
*                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
*                          Copyright © 2003-2014                               *
********************************************************************************/
package org.eastbar.web;

import org.eastbar.web.account.UserService;
import org.eastbar.web.account.biz.UserBO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceTest extends SpringTransactionalTestCase{

    @Autowired
    private UserService userService;

    @Test
    public void getUser() throws Exception{
        UserBO user = userService.get(1);
        System.out.println(user);
    }
}
