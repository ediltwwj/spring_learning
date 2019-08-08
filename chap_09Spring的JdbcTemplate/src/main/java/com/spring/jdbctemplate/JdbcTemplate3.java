package com.spring.jdbctemplate;

import com.spring.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 13967
 * @date 2019/8/8 13:03
 */
public class JdbcTemplate3 {

    public static void main(String[] args) {

        // 1、获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        // 2、获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate", JdbcTemplate.class);
        // 3、执行操作
        // 保存
        //jt.update("insert into account(name, money) values(?, ?)", "James", 2000f);
        // 更新
        //jt.update("update account set name=?, money=? where id=?", "Ducan", 2500f, 5);
        // 删除
        //jt.update("delete from account where id=?", 7);
        // 查询所有
//        List<Account> accounts = jt.query("select * from account where money > ?",
//                new BeanPropertyRowMapper<Account>(Account.class),1000f);
//        for(Account account : accounts){
//            System.out.println(account);
//        }
//        // 查询一个
//        List<Account> account = jt.query("select * from account where id = ?",
//                new BeanPropertyRowMapper<Account>(Account.class),8);
//        System.out.println(account.isEmpty() ? "没有内容": account.get(0));
        // 查询返回一行一列（使用聚合函数，但是不加group by子句）
        Integer count = jt.queryForObject("select count(*) from account where money > ?",Integer.class,1000f);
        System.out.println(count);
    }
}

/**
 * 定义Account的封装工具
 */
class AccountRowMapper implements RowMapper<Account>{

    /**
     * 把结果集中的数据封装到Account中,由Spring把每个Account加到集合中
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("id"));
        account.setName(rs.getString("name"));
        account.setMoney(rs.getFloat("money"));
        return account;
    }
}
