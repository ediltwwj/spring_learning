package com.spring.test;

import com.spring.domain.Account;
import com.spring.service.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author 13967
 * @date 2019/8/4 17:07
 *
 * 使用Junit单元测试，测试我们的配置
 */
public class AccountServiceTest {

    @Test
    public void testFindAll(){

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、得到业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3、执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }

    }

    @Test
    public void testFindOne(){

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、得到业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3、执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave(){

        Account account = new Account();
        account.setName("July");
        account.setMoney(2000f);

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、得到业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3、执行方法
        as.saveAccount(account);
        System.out.println("成功保存...");
    }

    @Test
    public void testUpdate(){

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、得到业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3、执行方法
        Account account = as.findAccountById(4);
        account.setMoney(3000f);
        as.updateAccount(account);
        System.out.println("成功更新...");
    }

    @Test
    public void testDelete(){

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、得到业务层对象
        AccountService as = ac.getBean("accountService", AccountService.class);
        // 3、执行方法
        as.deleteAccount(4);
        System.out.println("成功删除...");
    }
}
