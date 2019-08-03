package com.spring.ui;

import com.spring.service.AccountService;
import com.spring.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author 13967
 * @date 2019/8/3 19:23
 * 模拟表现层，用于调业务层
 */
public class Client {

    public static void main(String[] args) {

        // 1、获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、根据ID获取bean对象
        // AccountService as = (AccountService)ac.getBean("accountService");
        // AccountService as = (AccountService)ac.getBean("accountService2");
        AccountService as = (AccountService)ac.getBean("accountService3");

        as.saveAccount();
    }
}
