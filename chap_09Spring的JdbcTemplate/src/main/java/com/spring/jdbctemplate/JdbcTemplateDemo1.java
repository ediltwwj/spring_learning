package com.spring.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author 13967
 * @date 2019/8/8 12:41
 *
 * JdbcTemplate的最基本用法
 */
public class JdbcTemplateDemo1 {

    public static void main(String[] args) {

        // 准备数据源，spring的内置数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_01");
        ds.setUsername("root");
        ds.setPassword("123456");
        // 1、创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();
        // 给jt设置数据源
        jt.setDataSource(ds);
        // 2、执行操作
        jt.execute("insert into account(name, money) values('Harden', 1000)");
    }
}
