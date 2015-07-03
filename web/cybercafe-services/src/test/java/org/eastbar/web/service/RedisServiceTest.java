/**
 * 上海交通大学-鹏越惊虹信息技术发展有限公司
 *         Copyright © 2003-2014
 */
package org.eastbar.web.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cindy-jia
 * @date 2015年01月20
 * @time 上午10:50
 * @description :
 */
public class RedisServiceTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("测试开始");
        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:applicationContext-jedis.xml");
        //这里已经配置好,属于一个redis的服务接口
        RedisTemplate redisTemplate=app.getBean("redisTemplate",RedisTemplate.class);
        //test  string
        ValueOperations opsForValue = redisTemplate.opsForValue();

        opsForValue.set("string:name", "achuan");
        opsForValue.set("string:id","1");

        Object name = opsForValue.get("string:name");
        System.out.println(opsForValue.get("*"));
        System.out.println(name);

        //test list
        ListOperations opsForList = redisTemplate.opsForList();

        String keyName = "ListKey";

        redisTemplate.delete(keyName);

        opsForList.leftPush(keyName, "zhangsan");
        opsForList.leftPush(keyName, "lisi");
       // opsForList.leftPush(keyName, "wangwu", "zhaoliu", "qianqi");

        Long size = opsForList.size(keyName);
        System.out.println("size:" + size);

        for (long i = 0; i <size; i++) {
            Object value = opsForList.index(keyName,i);
            System.out.println("i:"+i+",value:"+value.toString());
        }

        Object leftPop = opsForList.leftPop(keyName);
        System.out.println("after pop size:"+opsForList.size(keyName));
        System.out.println(opsForValue.get("*"));

        User user=new User();
        user.setId(1);
        user.setUsername("jxd");
        user.setPassword("jxd");

        User user1=new User();
        user1.setId(2);
        user1.setUsername("jack");
        user1.setPassword("jxd");

        Map<String,Object> users=new HashMap<>();
        users.put(user.getId()+"",user);
        users.put(user1.getId()+"",user1);

        opsForList.leftPush(keyName, user.toString());
        System.out.println(opsForList.size(keyName));
        System.out.println(opsForList.range(keyName,0,4));

        String key="user:1";
        BoundHashOperations<String, String, String> boundHashOperations=redisTemplate.boundHashOps(key);
        Map<String,String> data=new HashMap<>();
        data.put("username",user.getUsername());
        data.put("password",user.getPassword());
        boundHashOperations.putAll(data);
        key="user:2";
        boundHashOperations=redisTemplate.boundHashOps(key);
        Map<String,String> data1=new HashMap<>();
        data1.put("username",user1.getUsername());
        data1.put("password",user1.getPassword());
        boundHashOperations.putAll(data1);


        redisTemplate.opsForHash().put("user1:"+user1.getId(),user1.getId()+"",user1.toString() );
        System.out.println("获取hash中的数据："+redisTemplate.opsForHash().get("user1:"+user1.getId()+"",user1.getId()+""));
        System.out.println("user1中的key值："+redisTemplate.opsForHash().keys(redisTemplate.opsForHash().get("user1:"+user1.getId()+"",user1.getId()+"")));

        System.out.println(key);
        System.out.println(boundHashOperations.get(key));
        System.out.println(boundHashOperations.getOperations());
        System.out.println(boundHashOperations.keys());
        System.out.println(boundHashOperations.size());
        System.out.println(boundHashOperations.entries());
        System.out.println(boundHashOperations.getKey());

        redisTemplate.opsForHash();
    }
}
