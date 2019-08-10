package com.spring.service;

import com.spring.domain.Account;

/**
 * @author 13967
 * @date 2019/8/10 11:47
 *
 * 账户的业务层接口
 */
public interface AccountService {

    /**
     * 根据Name查找账户
     * @param name
     * @return
     */
    Account findAccountByName(String name);

    /**
     * 转账
     * @param sourceName  转出账户名称
     * @param targetName  转入账户名称
     * @param money  转账金额
     */
    void transfer(String sourceName, String targetName, Float money);
}
