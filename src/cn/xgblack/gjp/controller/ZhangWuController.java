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
     * @author      小光
     * @date        2019/5/6 15:29
     * description  //TODO
     * @param       [startDate, endDate]
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
