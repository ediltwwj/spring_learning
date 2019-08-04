package com.spring.ui;

import com.spring.dao.AccountDao;
import com.spring.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 13967
 * @date 2019/8/4 10:49
 */
public class Client {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        AccountService as = (AccountService)ac.getBean("accountService");
//        AccountService as2 = (AccountService)ac.getBean("accountService");

//        System.out.println(as);
//
//        AccountDao adao = ac.getBean("accountDao", AccountDao.class);
//        System.out.println(adao);
//        System.out.println(as == as2);
        as.saveAccount();
        ac.close();
    }
}
