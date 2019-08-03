package com.spring.ui;

import com.spring.dao.AccountDao;
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

    /**
     * 获取spring的Ioc核心容器，并根据ID获取对象
     *
     * ApplicationContext的三个常用实现类:
     *      ClassPathXmlApplicationContext: 它可以加载类路径下的配置文件，要求配置文件必须在类路径下,不在，无法使用（常用方式）
     *      FileSystemXmlApplicationContext: 它可以加载磁盘任意路径下的配置文件（必须有访问权限）
     *      AnnotationConfigApplicationContext: 它是用于读取注解创建容器的，是明天的内容
     *
     * 核心容器的两个接口引发出的问题:
     *   ApplicationContext:    单例对象使用   实际开发采用此接口
     *      它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。也就是说一读取完配置文件就马上创建配置文件中配置的对象
     *   BeanFactory:   多例对象使用
     *      它在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。也就是说什么时候根据id获取对象了，什么时候才真正创建对象
     * @param args
     */
    public static void main(String[] args) {

//        // 1、获取核心容器对象
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
//        // 2、根据ID获取bean对象
//        AccountService as = (AccountService)ac.getBean("accountService");
//        AccountDao adao = ac.getBean("accountDao", AccountDao.class);
//
//        System.out.println(as);
//        System.out.println(adao);
//        as.saveAccount();

        // ---------- BeanFactory ----------
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        AccountService as = (AccountService)factory.getBean("accountService");
        System.out.println(as);
    }
}
