package com.spring.service.impl;

import com.spring.service.AccountService;

import java.util.Date;

/**
 * @author 13967
 * @date 2019/8/3 22:03
 */
public class AccountServiceImpl2 implements AccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount(){

        System.out.println("service中的saveAccount方法执行了||" + name + "," + age + "," + birthday);
    }
}
