package com.spring.service.impl;

import com.spring.dao.AccountDao;
import com.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


/**
 * @author 13967
 * @date 2019/8/4 10:52
 *
 * 曾经XML配置
 * <bean id="accountService" class="com.spring.service.impl.AccountServiceImpl"
 *      scope="" init-method="" destroy-method="">
 *      <property name="" value=""/ref=""></property>
 * </bean>
 *
 * 用于创建对象的
 *      他们的作用就和在XML配置文件中编写一个<bean>标签实现的功能是一样的
 *      Component:
 *          作用: 把当前类对象存入spring容器中
 *          属性:
 *              value: 用于指定bean的id，当不写时，它的默认值是当前类名且首字母改为小写
 *      Controller: 一般使用在表现层
 *      Service: 一般用在业务层
 *      Repository: 一般用于持久层
 *      以上三个注解他们的作用和属性和Component是一样的，他们三个是spring框架为我们
 *          提供明确的三层使用的注解，使我们三层对象更加清晰
 *
 * 用于注入数据的
 *      他们的作用就和在XML配置文件中的bean标签中写一个<property>标签的作用是一样的
 *      Autowired:
 *          作用: 自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *                如果IOC容器中没有任何bean类型和要注入的变量类型匹配，则报错
 *                如果IOC容器中有多个类型匹配时: 先看变量类型，再看变量名称，如果有唯一一个变量名称一样则成功，否则报错
 *          出现位置: 可以是变量上，也可以是方法上
 *          细节: 在使用注解注入时，set方法就不是必须的
 *      Qualifier:
 *          作用: 在按照类注入的基础之上再按照名称注入，它在给类成员注入时，不能单独使用，但是给方法注入参数时可以（稍后讲）
 *          属性:
 *              value: 用于指定注入bean的id
 *      Resource:
 *          作用: 直接按照bean的id注入，它可以独立使用
 *          属性:
 *              name: 用于指定bean的id
 *      另外: 以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型都无法使用上诉注解实现
 *      Value:
 *          作用: 用于注入基本类型和String类型的数据
 *          属性:
 *              value: 用于指定数据的值，它可以使用spring中SpEl（也就是spring的el表达式）
 *                  SpEl的写法: ${表达式}
 *
 * 用于改变作用范围的
 *      他们的作用就和在bean标签中使用scope属性实现的功能是一样的
 *      Scope:
 *          作用: 用于指定bean的作用范围
 *          属性:
 *              value: 指定范围的取值，常用取值: singleton, prototype 默认单例
 * 和生命周期相关(了解)
 *      他们的作用就和在bean标签中使用Init-method和destroy-method的作用是一样的
 *      PreDestroy:
 *          作用: 用于指定销毁方法
 *      PostConstruct:
 *          作用: 用于指定销毁方法
 */
@Service(value = "accountService")
// @Scope("prototype")
public class AccountServiceImpl implements AccountService {

//    @Autowired
//    @Qualifier("accountDao1")
    @Resource(name = "accountDao2")
    private AccountDao accountDao = null;

    @PostConstruct
    public void init(){

        System.out.println("AccountService初始化方法...");
    }

    @PreDestroy
    public void destroy(){

        System.out.println("AccountService销毁方法...");
    }

    public void saveAccount() {

        accountDao.saveAccount();
    }
}
