package cn.xgblack.gjp.tools;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 小光
 * @date 2019/5/6 13:00
 * className: JDBCUtils
 * description: 获取数据库连接的工具类
 *              实现连接池dbcp
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class JDBCUtils {
    private static BasicDataSource dataSource = new BasicDataSource();
    private static String className;
    private static String url;
    private static String userName;
    private static String password;

    /**
     * 静态代码块，实现参数设置
     */
    static {

        //读取配置文件
        try {
            readConfig();

            //数据库连接必须配置的
            dataSource.setDriverClassName(className);
            dataSource.setUrl(url);
            dataSource.setUsername(userName);
            dataSource.setPassword(password);


            //对连接池中的连接数量进行配置
            //初始化连接数
            dataSource.setInitialSize(10);
            //最大连接数量
            dataSource.setMaxTotal(10);
            //最大空闲连接数
            dataSource.setMaxIdle(5);
            //最小空闲连接数
            dataSource.setMinIdle(2);

        } catch (IOException e) {
            throw new RuntimeException("数据库配置文件读取失败");
        }


    }

    /**
     * @author      小光
     * @date        2019/5/5 15:54
     * description  读取配置文件
     */
    private static void readConfig() throws IOException {
        //读取本地配置文件
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");

        Properties properties = new Properties();
        properties.load(is);

        //获得数据库的连接
        className = properties.getProperty("className");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

}
