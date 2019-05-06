package cn.xgblack.gjp.test;

import cn.xgblack.gjp.domain.ZhangWu;
import cn.xgblack.gjp.tools.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 小光
 * @date 2019/5/6 15:07
 * className: Test
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class Test {
    public static void main(String[] args) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        //查询账务的SQL语句
        String sql = "SELECT createtime FROM gjp_zhangwu WHERE zwid = 1;";
        List<ZhangWu> list = null;
        try {
            list = queryRunner.query(sql, new BeanListHandler<>(ZhangWu.class));

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("查询 所有账务 失败");
        }
        //System.out.println("ID\t\t类别\t\t账户\t\t金额\t\t时间\t\t说明");
        //遍历集合，输出结果到控制台
        for (ZhangWu zhangwu:list){

            System.out.println(zhangwu.getCreatetime());
        }
    }
}
