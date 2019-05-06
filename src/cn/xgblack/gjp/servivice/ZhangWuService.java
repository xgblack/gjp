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
