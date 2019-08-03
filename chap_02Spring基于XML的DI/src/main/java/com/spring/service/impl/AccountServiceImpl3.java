package com.spring.service.impl;

import com.spring.service.AccountService;

import java.util.*;

/**
 * @author 13967
 * @date 2019/8/3 22:24
 */
public class AccountServiceImpl3 implements AccountService {

    private String[] myStrs;
    private List<String> myList;
    private Set<String> mySet;
    private Map<String, String> myMap;
    private Properties myPros;

    public void setMyStrs(String[] myStrs) {
        this.myStrs = myStrs;
    }

    public void setMyList(List<String> myList) {
        this.myList = myList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyPros(Properties myPros) {
        this.myPros = myPros;
    }

    public void saveAccount() {

        System.out.println(Arrays.toString(myStrs));
        System.out.println(myList);
        System.out.println(mySet);
        System.out.println(myMap);
        System.out.println(myPros);
    }
}
