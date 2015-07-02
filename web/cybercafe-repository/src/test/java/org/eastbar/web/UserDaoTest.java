//package org.eastbar.web;///********************************************************************************
////*                  上海交通大学-鹏越惊虹信息技术发展有限公司                       *
////*                          Copyright © 2003-2014                               *
////********************************************************************************/
//package com.lins.cybercafe;
//
//import com.lins.cybercafe.account.dao.UserDao;
//import com.lins.cybercafe.account.entity.User;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springside.modules.security.utils.Digests;
//import org.springside.modules.test.spring.SpringTransactionalTestCase;
//import org.springside.modules.utils.Encodes;
//
///**
//* @author C.lins@aliyun.com
//* @date 2014年08月17
//* @time 下午2:05
//* @description :
//*/
//@DirtiesContext
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
//public class UserDaoTest extends SpringTransactionalTestCase{
//    public static final String HASH_ALGORITHM = "SHA-1";
//    public static final int HASH_INTERATIONS = 1024;
//    private static final int SALT_SIZE = 8;
//
//    @Autowired
//    private UserDao userDao;
//
//    @Test
//    public void getUser() throws Exception{
////        User user = userDao.findByLoginName("admin");
//        User user = userDao.getUserRole(2);
//        System.out.println(user);
//        System.out.println("===============================================");
//        entryptPassword(user,"pengyue");
//        System.out.println(user);
//    }
//    @Test
//    public void entryptPassword() throws Exception{
//        String plainPassword="pengyue";
//        byte[] salt = Digests.generateSalt(SALT_SIZE);
//        System.out.println(Encodes.encodeHex(salt));
//
//        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
//        System.out.println(Encodes.encodeHex(hashPassword));
//
////        cfbd3694ae295e91
////        f26e8dedcfeffa022ae8d6b7897a35c0001b4398
//
//        byte[] salts = Encodes.decodeHex("cfbd3694ae295e91");
//        byte[] hashPasswords = Digests.sha1("f26e8dedcfeffa022ae8d6b7897a35c0001b4398".getBytes(), salts, HASH_INTERATIONS);
//
//    }
//}
