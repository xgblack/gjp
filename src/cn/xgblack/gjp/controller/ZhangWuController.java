package cn.xgblack.gjp.controller;

import cn.xgblack.gjp.domain.ZhangWu;
import cn.xgblack.gjp.servivice.ZhangWuService;

import java.util.List;

/**
 * @author 小光
 * @date 2019/5/6 13:20
 * className: ZhangWuController
 * description: 控制器层
 *              接收视图层的数据，数据传递给service层
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class ZhangWuController {
    private ZhangWuService service = new ZhangWuService();

    /**
     * 定义方法：备份账务
     *         调用service层的方法
     *         被view层调用
     * @return  boolean flag  是否备份成功
     */
    public boolean backupZhangWu(){
        return service.backupZhangWu();
    }

    /**
     * 定义方法：删除账务
     *         被view层调用，调用service层的方法
     * @param ids
     * @return int rows   操作数据库的条数
     */
    public int deleteZhangWu(String[] ids) {
        return service.deleteZhangWu(ids);
    }


    /**
     * 定义方法：修改账务方法
     *          此方法为View层调用，调用Service层
     * @param zhangWu
     * @return int   数据库操作条数，此处应为1或者0
     */
    public int editZhangWu(ZhangWu zhangWu){
        return service.editZhangWu(zhangWu);
    }

    /**
     * 定义方法：添加账务方法
     *          此方法为View层调用，调用Service层
     * @param zhangWu
     * @return int   数据库操作条数，此处应为1或者0
     */
    public int addZhangWu(ZhangWu zhangWu){
        return service.addZhangWu(zhangWu);
    }


    /**
     * @author      小光
     * @date        2019/5/6 15:29
     * description  //TODO
     * @return      java.util.List<cn.xgblack.gjp.domain.ZhangWu>
     */
    public List<ZhangWu> select(String startDate, String endDate) {
        return service.select(startDate, endDate);
    }


    /**
     * @author      小光
     * @date        2019/5/6 13:55
     * description  控制层定义方法，实现查询所有的账务数据
     *              方法由视图层调用，调用service层
     */
    public List<ZhangWu> selectAll(){
        return service.selectAll();
    }
}
