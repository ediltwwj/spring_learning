package com.spring.service.impl;

import com.spring.service.AccountService;

/**
 * @author 13967
 * @date 2019/8/3 19:21
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

    public AccountServiceImpl(){
        System.out.println("AccountServiceImpl对称创建了");
    }

    public void saveAccount(){

        System.out.println("service中的saveAccount方法执行了");
    }

    public void init(){

        System.out.println("AccountServiceImpl Init...");
    }

    public void destroy(){

        System.out.println("AccountServiceImpl Destroy...");
    }
}
