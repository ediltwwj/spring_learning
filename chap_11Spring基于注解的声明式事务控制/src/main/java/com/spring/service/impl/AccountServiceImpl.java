package com.spring.service.impl;

import com.spring.dao.AccountDao;
import com.spring.domain.Account;
import com.spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 13967
 * @date 2019/8/10 11:50
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)  // 只读型事务的配置
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }


    // 需要读写型事务配置
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
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
        //int i = 10/0;
        // 2.6、更新转入账户
        accountDao.updateAccount(target);
    }
}
