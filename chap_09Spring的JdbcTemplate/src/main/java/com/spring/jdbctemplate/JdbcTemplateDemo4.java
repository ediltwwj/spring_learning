package com.spring.jdbctemplate;

import com.spring.dao.AccountDao;
import com.spring.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 13967
 * @date 2019/8/8 14:35
 */
public class JdbcTemplateDemo4 {

    public static void main(String[] args) {

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、获取对象
        AccountDao accountDao = ac.getBean("accountDao", AccountDao.class);

        Account account = accountDao.findAccountById(8);

        System.out.println(account);
    }
}
