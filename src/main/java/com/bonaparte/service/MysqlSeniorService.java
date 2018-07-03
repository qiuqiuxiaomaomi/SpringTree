package com.bonaparte.service;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangmingquan on 2018/6/29.
 * mysql高级功能
 *  Mysql的索引
 *  Mysql的存储引擎
 *  Mysql的连接池
 *  Mysql的执行计划，执行计划的优化
 *  Mysql的缓存
 */
@Service
public class MysqlSeniorService {
    public void mysqlBasicOp(){
        //申明connection对象
        Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        String mysqlUrl = "jdbc:mysql://mysql:3306/dbl";
        String user="user1";
        String password="password";
        List<Map<String, Object>> mapList = new ArrayList<>();
        try{
            Class.forName(driver);
            connection =  DriverManager.getConnection(mysqlUrl, user, password);
            if (!connection.isClosed()){
                //Statemment
                Statement statement = connection.createStatement();
                String sql = "select * from p_charge";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    Map<String, Object> map = new HashedMap();
                    map.put("money", resultSet.getDouble("money"));
                    map.put("id", 1);
                }
                resultSet.close();
            }
            connection.close();
        }catch (Exception e){
            System.out.println("数据库连接异常");
        }
    }
}
