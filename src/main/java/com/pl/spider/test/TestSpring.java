package com.pl.spider.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@Component
public class TestSpring extends AbstractJUnit4SpringContextTests {

    @Autowired
    UserDao userDao;

   @Test
    public void testDoInsert() throws Exception {
        User user = new User();
        user.setUsername("李四");
        user.setPassword("123");
        userDao.save(user);
    }



//    public  static void main(String[] args){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
//
//        context.start();
//        User user = new User();
//        user.setUsername("张三");
//        user.setPassword("123");
//        UserDao userDao = (UserDao) context.getBean("userDao");
//        userDao.save(user);
//    }
}
