package com.spring.factory;

import com.spring.service.AccountService;
import com.spring.service.impl.AccountServiceImpl;

/**
 * @author 13967
 * @date 2019/8/3 20:09
 */
public class StaticFactory {

    public static AccountService getAccountService(){

        return new AccountServiceImpl();
    }
}
