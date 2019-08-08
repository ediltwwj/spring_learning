package com.spring.dao;

import com.spring.domain.Account;

/**
 * @author 13967
 * @date 2019/8/8 14:23
 *
 * 账户的持久层接口
 */
public interface AccountDao {

    /**
     * 根据id查询账户
     * @param accountId
     * @return
     */
    Account findAccountById(Integer accountId);

    /**
     * 根据名称查询用户
     * @param accountName
     * @return
     */
    Account findAccountByName(String accountName);

    /**
     * 更新账户
     * @param account
     */
    void updateAccount(Account account);
}
