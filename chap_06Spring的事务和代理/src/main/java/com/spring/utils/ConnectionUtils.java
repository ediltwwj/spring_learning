package com.spring.utils;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author 13967
 * @date 2019/8/6 10:55
 *
 * 连接的工具类，它用于从数据源中获取一个连接，并且实现和线程的绑定
 */
public class ConnectionUtils {

    private ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的连接
     * @return
     */
    public Connection getThreadConnection(){

        try{
            // 1、先从ThreadLocal上获取
            Connection conn = tl.get();
            // 2、判断当前线程上是否有连接
            if(conn == null){
                // 3、从数据源中获取一个连接，并且存入ThreadLocal
                conn = dataSource.getConnection();
                tl.set(conn);
            }
            // 返回当前线程上的连接
            return conn;
        }catch(Exception e){
            throw new RuntimeException();
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection(){
        tl.remove();
    }
}
