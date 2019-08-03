package com.spring.service.impl;

import com.spring.dao.AccountDao;
import com.spring.dao.impl.AccountDaoImpl;
import com.spring.service.AccountService;

/**
 * @author 13967
 * @date 2019/8/3 19:21
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    public void saveAccount(){

        accountDao.saveAccount();
    }
}
