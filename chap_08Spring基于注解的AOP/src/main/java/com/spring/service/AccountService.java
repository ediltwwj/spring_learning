package com.spring.service;

/**
 * @author 13967
 * @date 2019/8/7 11:35
 *
 * 账号的业务层接口
 */
public interface AccountService {

    /**
     * 模拟保存账户
     */
    void saveAccount();

    /**
     * 模拟更新账户
     */
    void updateAccount(int i);

    /**
     * 模拟删除账户
     */
    int deleteAccount();
}
