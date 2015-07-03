/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web;

import org.eastbar.web.account.dao.UserDao;
import org.eastbar.web.account.entity.User;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTransactionalTestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年06月10
 * @time 下午3:59
 * @description :
 */
@DirtiesContext
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DataSourceTest extends SpringTransactionalTestCase {


    @Autowired
    private UserDao userDao;

    @Autowired
    private SqlSessionTemplate sqlSession;


    @Test
    public void getUser() throws Exception{

        System.out.println(sqlSession.getConnection());


      System.out.println(userDao.findByLoginName("admin").getId());
       // Map<String,Object> attr=new HashMap<>();
       // userDao.getAllUser(attr);
       // System.out.println(user);
        System.out.println("===============================================");
        //entryptPassword(user,"pengyue");
        //System.out.println(user);
    }


    public static void main(String[] args){

    }


}
