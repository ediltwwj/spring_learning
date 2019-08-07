package com.spring.service.impl;

import com.spring.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * @author 13967
 * @date 2019/8/7 11:37
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    public void saveAccount() {
        System.out.println("执行了保存...");
    }

    public void updateAccount(int i) {
        System.out.println("执行了更新...");
    }

    public int deleteAccount() {
        System.out.println("执行了删除...");
        return 0;
    }
}
