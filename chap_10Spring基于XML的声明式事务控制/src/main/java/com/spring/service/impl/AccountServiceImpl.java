package com.spring.service.impl;

import com.spring.dao.AccountDao;
import com.spring.domain.Account;
import com.spring.service.AccountService;


/**
 * @author 13967
 * @date 2019/8/10 11:50
 */
public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {

        this.accountDao = accountDao;
    }


    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }


    public void transfer(String sourceName, String targetName, Float money) {

        System.out.println("transfer....");

        // 2、执行操作
        // 2.1、根据名称查询转出账户
        Account source = accountDao.findAccountByName(sourceName);
        // 2.2、根据名称查询转入账户
        Account target = accountDao.findAccountByName(targetName);
        // 2.3、转出账户减钱
        source.setMoney(source.getMoney() - money);
        // 2.4、转入账户加钱
        target.setMoney(target.getMoney() + money);
        // 2.5、更新转出账户
        accountDao.updateAccount(source);
        int i = 10/0;
        // 2.6、更新转入账户
        accountDao.updateAccount(target);
    }
}
