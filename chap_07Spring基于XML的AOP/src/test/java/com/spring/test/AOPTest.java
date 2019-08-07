package com.spring.test;

import com.spring.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 13967
 * @date 2019/8/7 14:00
 *
 * 测试AOP的配置
 */
public class AOPTest {

    public static void main(String[] args) {

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、获取对象
        AccountService as = (AccountService)ac.getBean("accountService");

        as.saveAccount();
    }
}
