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

    public int deleteZhangWu(String[] ids) {
        int rows = 0;
        try {
            String sql = "DELETE FROM gjp_zhangwu WHERE zwid = ?;";
            //遍历数组，依次删除多条数据
            for (int i = 0; i < ids.length; i++) {
                queryRunner.update(sql, Integer.parseInt(ids[i]));
                rows++;
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("数据库删除失败");
        }
        return rows;
    }


    /**
     * 定义方法：修改账务
     * @param zhangwu
     * @return row  数据库操作条数
     */
    public int editZhangWu(ZhangWu zhangwu){
        String sql = "UPDATE gjp_zhangwu SET flname = ?,money = ?,zhanghu = ?,createtime = ?,description = ?  WHERE zwid = ?;";
        Object[] params = {
                zhangwu.getFlname(),
                zhangwu.getMoney(),
                zhangwu.getZhanghu(),
                zhangwu.getCreatetime(),
                zhangwu.getDescription(),
                zhangwu.getZwid()
        };
        int row = -1;
        try {
            row = queryRunner.update(sql, params);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }


    /**
     * 定义方法：添加账务
     *         此方法由Service层调用
     * @param zhangwu
     * @return row  返回数据库操作条数
     */
    public int addZhangWu(ZhangWu zhangwu) {
        String sql = "INSERT INTO gjp_zhangwu (flname,money,zhanghu,createtime,description) VALUES(?,?,?,?,?);";
        Object[] params = {zhangwu.getFlname(),zhangwu.getMoney(),zhangwu.getZhanghu(),zhangwu.getCreatetime(),zhangwu.getDescription()};

        //queryRunner.query(sql, params);
        try {
            int row = queryRunner.update(sql, params);
            return row;
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("数据库更新失败");
        }
    }



    /**
     * 条件查询
     * @param startDate
     * @param endDate
     * @return 返回List<ZhangWu>   为数据库查询结果
     */
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
