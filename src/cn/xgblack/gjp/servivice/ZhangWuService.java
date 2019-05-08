package cn.xgblack.gjp.servivice;

import cn.xgblack.gjp.dao.ZhangWuDao;
import cn.xgblack.gjp.domain.ZhangWu;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/6 13:17
 * className: ZhangWuService
 * description: 业务层类，接收上一层controller的数据
 *              经过计算，传递给dao层，操作数据库
 *              调用dao层中的类
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class ZhangWuService {
    private ZhangWuDao dao = new ZhangWuDao();

    /**
     * 定义方法：备份账务
     *         调用dao层的方法
     *         被controller层调用
     * @return  boolean flag  是否备份成功
     */
    public boolean backupZhangWu(){
        return dao.backupZhangWu();
    }


    /**
     * 定义方法：删除账务
     *         被controller层调用，调用dao层的方法
     * @param ids
     * @return int rows   操作数据库的条数
     */
    public int deleteZhangWu(String[] ids) {
        return dao.deleteZhangWu(ids);
    }


    /**
     * 定义方法：修改账务方法
     *          此方法为Controller层调用，调用dao层
     * @param zhangWu
     * @return int   数据库操作条数，此处应为1或者0
     */
    public int editZhangWu(ZhangWu zhangWu){
        return dao.editZhangWu(zhangWu);
    }


    /**
     * 定义方法：添加账务方法
     *          此方法调用dao层，被Controller层调用
     * @param zhangWu
     * @return
     */
    public int addZhangWu(ZhangWu zhangWu){
        return dao.addZhangWu(zhangWu);
    }

    /**
     * @author      小光
     * @date        2019/5/6 15:30
     * description  //TODO
     * @return      java.util.List<cn.xgblack.gjp.domain.ZhangWu>
     */
    public List<ZhangWu> select(String startDate, String endDate) {
        return dao.select(startDate, endDate);
    }

    /**
     * @author      小光
     * @date        2019/5/6 14:04
     * description  定义方法，实现查询所有账务数据
     *              由控制层调用，去调用dao层的方法
     * @return      java.util.List<cn.xgblack.gjp.domain.ZhangWu>
     */
    public List<ZhangWu> selectAll(){
        return dao.selectAll();
    }
}
