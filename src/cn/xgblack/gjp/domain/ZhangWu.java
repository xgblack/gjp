package cn.xgblack.gjp.domain;


import java.sql.Date;

/**
 * @author 小光
 * @date 2019/5/6 10:37
 * className: ZhangWu
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class ZhangWu {

    //主键列
    private int zwid;

    //分类名称
    private String flname;

    //金额
    private double money;

    //账户
    private String zhanghu;

	//创建日期
    private Date createtime;

    //描述
    private String description;



    public ZhangWu() {
    }

    public ZhangWu(int zwid, String flname, double money, String zhanghu, Date createtime, String description) {
        this.zwid = zwid;
        this.flname = flname;
        this.money = money;
        this.zhanghu = zhanghu;
        this.createtime = createtime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ZhangWu{" +
                "zwid=" + zwid +
                ", flname='" + flname + '\'' +
                ", money=" + money +
                ", zhanghu='" + zhanghu + '\'' +
                ", createtime='" + createtime + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getZwid() {
        return zwid;
    }

    public void setZwid(int zwid) {
        this.zwid = zwid;
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getZhanghu() {
        return zhanghu;
    }

    public void setZhanghu(String zhanghu) {
        this.zhanghu = zhanghu;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
