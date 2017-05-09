package com.pl.spider.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/4/21.
 */
@Component
public class UserDao {
    @Autowired
    @Resource(name = "spiderJdbcTemplate")
    private JdbcTemplate template;

    public void save(User user) {
        template.update("insert into user(username,password) values(?,?)", new Object[]{user.getUsername(), user.getPassword()}, new int[]{
                java.sql.Types.VARCHAR, java.sql.Types.VARCHAR});
    }
}
