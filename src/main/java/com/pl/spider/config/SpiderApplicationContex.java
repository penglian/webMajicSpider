package com.pl.spider.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by luweineng on 2016/7/19.
 */
public class SpiderApplicationContex {
    public static ApplicationContext applicationContext;

    static {
        SpiderApplicationContex.init();
    }

    protected static void init() {
        applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    }
}
