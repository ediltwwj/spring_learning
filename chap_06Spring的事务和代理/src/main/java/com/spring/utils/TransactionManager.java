package com.spring.utils;

/**
 * @author 13967
 * @date 2019/8/6 11:04
 *
 * 和事务管理相关的工具类,它包含了开启事务，提交事务，回滚事务和释放连接
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开启事务
     */
    public void beginTranscation(){
        try{
            connectionUtils.getThredConnection().setAutoCommit(false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 提交事务
     */
    public void commit(){
        try{
            connectionUtils.getThredConnection().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public void rollback(){
        try{
            connectionUtils.getThredConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 释放连接
     */
    public void release(){
        try{
            connectionUtils.getThredConnection().close();  // 并不是真正关闭连接，只是还给了连接池
            connectionUtils.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
