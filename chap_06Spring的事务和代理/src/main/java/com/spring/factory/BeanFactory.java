package com.spring.factory;

import com.spring.domain.Account;
import com.spring.service.AccountService;
import com.spring.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 13967
 * @date 2019/8/6 14:57
 *
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

    private AccountService accountService;

    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {

        this.txManager = txManager;
    }

    // final是因为匿名内部类访问外界变量
    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service的代理对象
     * @return
     */
    public AccountService getAccountService() {

        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        Object rtValue = null;
                        try{
                            // 1、开始事务
                            txManager.beginTranscation();
                            // 2、执行操作
                            rtValue = method.invoke(accountService, args);
                            // 3、提交事务
                            txManager.commit();
                            // 4、返回结果
                            return rtValue;
                        }catch (Exception e){
                            // 5、回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            // 6、释放连接
                            txManager.release();
                        }
                    }
                });
    }
}
