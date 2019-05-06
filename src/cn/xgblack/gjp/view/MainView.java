package cn.xgblack.gjp.view;

import cn.xgblack.gjp.controller.ZhangWuController;
import cn.xgblack.gjp.domain.ZhangWu;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 小光
 * @date 2019/5/6 13:21
 * className: MainView
 * description: 视图层，用户看到和操作的界面
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
public class MainView {
    private ZhangWuController controller = new ZhangWuController();

    /**
     * 实现界面效果
     * 接收用户输入
     * 根据数据，调用不同的功能
     */
    public void run(){
        //创建Scanner类的对象
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("---------------管家婆家庭记账软件---------------");
            System.out.println("1.添加账务\t\t2.编辑账务\t\t3.删除账务\t\t4.查询账务\t\t5.退出系统");
            System.out.println("请输入要操作的功能序号[1-5]:");

            //接收用户菜单选择
            int choose = scanner.nextInt();
            //对选择的菜单进行判断，调用不同的功能
            if (choose == 1){
                //1.添加账务，调用添加账务方法
                //break;
            }else if (choose == 2){
                //2.编辑账务，调用编辑账务方法
                //break;
            }else if (choose == 3){
                //3.删除账务，调用删除账务方法
                //break;
            }else if (choose == 4){
                //4.查询账务，调用查询账务方法
                selectZhangWu();
                //break;
            }else if (choose == 5){
                //5.退出系统
                System.exit(0);
                //break;
            }else {
                System.out.println("输入错误");
            }


        }
    }

    /**
     * 定义方法，选择查询方式
     * 1.查询所有2.条件查询
     */
    public void selectZhangWu(){
        System.out.println("1.查询所有\t\t2.按条件查询");
        System.out.println("请输入要操作的功能序号[1-2]:");

        Scanner scanner = new Scanner(System.in);
        int selectChoose = scanner.nextInt();
        if (selectChoose == 1){
            //1.查询所有
            selectAll();
        }else if (selectChoose == 2){
            //条件查询
            select();
        }else {
            System.out.println("无此功能，返回上一步");
            run();
        }
    }

    /**
     * 查询所有数据
     */
    public void selectAll(){
        //调用控制层方法查询账务数据
        List<ZhangWu> list = controller.selectAll();

        pritList(list);
    }

    /**
     * 条件查询
     */
    public void select(){
        System.out.println("查询条件，输入日期格式XXXX-XX-XX");
        Scanner scanner = new Scanner(System.in);
        //查询的开始日期
        String startDate = "";
        //查询的结束日期
        String endDate = "";

        while (true){
            System.out.print("请输入开始日期：");
            startDate = scanner.nextLine();
            if (isDate(startDate)){
                break;
            }else {
                System.out.println("日期格式错误，请重新输入，日期格式为XXXX-XX-XX");;
            }
        }
        while (true){
            System.out.print("请输入结束日期：");
            endDate = scanner.nextLine();
            if (isDate(endDate)){
                break;
            }else {
                System.out.println("日期格式错误，请重新输入，日期格式为XXXX-XX-XX");;
            }
        }

        //调用Controller层的
        List<ZhangWu> list = controller.select(startDate,endDate);
        if (list != null){
            pritList(list);
        }else {
            System.out.println("没有查询到数据");
        }

    }

    /**
     * 遍历打印List<ZhangWu>集合
     * @param list
     */
    private static void pritList(List<ZhangWu> list) {
        //输出表头
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n","ID","类别","账户","金额","时间","说明");
        //遍历集合，输出结果到控制台
        for (ZhangWu zhangwu:list){

            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %n",
                    zhangwu.getZwid(),zhangwu.getFlname(),zhangwu.getZhanghu(),
                    zhangwu.getMoney(),zhangwu.getCreatetime(),zhangwu.getDescription()
            );
        }
    }

    /**
     * 判断输入日期格式是否正确
     * @param str
     * @return boolean
     */
    private static boolean isDate(String str){
        String pattern = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        return m.matches();
    }

}
