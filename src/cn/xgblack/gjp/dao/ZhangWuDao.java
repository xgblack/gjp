package cn.xgblack.gjp.dao;

import cn.xgblack.gjp.domain.ZhangWu;
import cn.xgblack.gjp.tools.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * 定义方法：备份账务到xml文件
     * @return
     */
    public boolean backupZhangWu(){
        //用于表示备份是否成功的状态，默认为false
        boolean flag = false;

        //创建Document对象
        Document doc = DocumentHelper.createDocument();
        //XML根元素
        Element root = doc.addElement("ZhangWu");

        //添加注释
        root.addComment("gjp_zhangwu备份（xml）");
        root.addComment("备份时间：" + new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));

        //美化XML输出格式
        OutputFormat format = OutputFormat.createPrettyPrint();

        //获取当前系统时间，精确到毫秒数，用来作为文件名的后半部分
        //定义文件名
        String filename = "ZWbackup_" + System.currentTimeMillis() + ".xml";
        File destBackup = new File("zw_backup/", filename);

        //创建XMLWriter对象，用来写入XML文件
        XMLWriter xmlWriter = null;
        try {
            xmlWriter = new XMLWriter(new FileWriter(destBackup), format);

            //查询全部数据，此处直接调用查询全部的方法
            List<ZhangWu> list = selectAll();

            //循环遍历结果集，并将结果写入XML文件
            for (ZhangWu zhangwu:list){
                //子元素
                Element zw = root.addElement("zhangwu");
                //zhangwu的子元素
                Element flname = zw.addElement("flname");
                Element money = zw.addElement("money");
                Element zhanghu = zw.addElement("zhanghu");
                Element createtime = zw.addElement("createtime");
                Element description = zw.addElement("description");
                //添加属性zwid，并赋值
                zw.addAttribute("zwid", zhangwu.getZwid() + "");

                //标签写入值（数据库查询的数据）
                flname.addText(zhangwu.getFlname());
                money.addText(zhangwu.getMoney() + "");
                zhanghu.addText(zhangwu.getZhanghu());
                createtime.addText(zhangwu.getCreatetime() + "");
                description.addText(zhangwu.getDescription());

            }
            //写入xml文件
            xmlWriter.write(doc);

            xmlWriter.flush();

            //将状态更改为true，即备份成功
            flag = true;
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("文件写入失败");
        }finally {
            if (xmlWriter != null){
                try {
                    xmlWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                    throw new RuntimeException("文件写入失败(资源释放失败)");
                }
            }
        }

        //返回备份状态
        return flag;
    }

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
