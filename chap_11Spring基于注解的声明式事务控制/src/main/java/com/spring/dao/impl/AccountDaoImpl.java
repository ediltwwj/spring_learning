package com.spring.dao.impl;

import com.spring.dao.AccountDao;
import com.spring.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 13967
 * @date 2019/8/8 14:26
 *
 * 账户的持久层实现类
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account findAccountByName(String accountName) {
        List<Account>accounts = jdbcTemplate.query("select * from account where name=?",
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
        jdbcTemplate.update("update account set name=?, money=? where id=?",
                account.getName(), account.getMoney(), account.getId());
    }
}
