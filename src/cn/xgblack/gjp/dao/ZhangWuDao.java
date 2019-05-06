package cn.xgblack.gjp.dao;

import cn.xgblack.gjp.domain.ZhangWu;
import cn.xgblack.gjp.tools.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小光
 * @date 2019/5/6 13:14
 * className: ZhangWuDao
 * description: 实现对数据表gjp_zhangwu  数据的增删改查操作
 *              使用JDBCUtils工具类实现
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class ZhangWuDao {
    private static QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    public List<ZhangWu> select(String startDate, String endDate) {
        String sql = "SELECT * FROM gjp_zhangwu WHERE createtime BETWEEN ? AND ? ;";
        try {
            List<ZhangWu> list = queryRunner.query(sql, new BeanListHandler<>(ZhangWu.class),startDate,endDate);
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("查询 账务 失败");
        }
    }


    /**
     * @author      小光
     * @date        2019/5/6 13:58
     * description  查询数据库，获取所有的账务信息
     *              由service调用
     *  @return      java.util.List<cn.xgblack.gjp.domain.ZhangWu>
     */
    public List<ZhangWu> selectAll(){
        //查询账务的SQL语句
        String sql = "SELECT * FROM gjp_zhangwu;";

        try {
            List<ZhangWu> list = queryRunner.query(sql, new BeanListHandler<>(ZhangWu.class));
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("查询 所有账务 失败");
        }

    }

}
