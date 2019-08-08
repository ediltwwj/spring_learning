package com.spring.dao.impl;

import com.spring.dao.AccountDao;
import com.spring.domain.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @author 13967
 * @date 2019/8/8 14:26
 *
 * 账户的持久层实现类
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao {


    public Account findAccountById(Integer accountId) {
        List<Account> accounts =  super.getJdbcTemplate().query("select * from account where id=?",
                new BeanPropertyRowMapper<Account>(Account.class), accountId);
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public Account findAccountByName(String accountName) {
        List<Account>accounts = super.getJdbcTemplate().query("select * from account where name=?",
                new BeanPropertyRowMapper<Account>(Account.class), accountName);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("结果集不唯一");
        }

        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        super.getJdbcTemplate().update("update account set name=?, money=?, where id=?",
                account.getName(), account.getMoney(), account.getId());
    }
}
