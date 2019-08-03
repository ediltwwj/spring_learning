package com.spring.factory;

import com.spring.service.AccountService;
import com.spring.service.impl.AccountServiceImpl;

/**
 * @author 13967
 * @date 2019/8/3 19:57
 * 模拟一个工厂类，该类可能存在jar包中，我们无法通过
 * 修改源码的方式来提供默认构造函数
 */
public class InstanceFactory {

    public AccountService getAccountService(){

        return new AccountServiceImpl();
    }
}
