package com.spring.dao.impl;

import com.spring.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * @author 13967
 * @date 2019/8/4 14:56
 */
@Repository("accountDao2")
public class AccountDaoImpl2 implements AccountDao {

    public void saveAccount() {
        System.out.println("保存了账户222222...");
    }
}
