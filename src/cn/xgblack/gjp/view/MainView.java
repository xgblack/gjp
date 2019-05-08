package cn.xgblack.gjp.view;

import cn.xgblack.gjp.controller.ZhangWuController;
import cn.xgblack.gjp.domain.ZhangWu;
import com.sun.deploy.util.SyncAccess;

import java.sql.Date;
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
            System.out.println("1.添加账务\t\t2.编辑账务\t\t3.删除账务\t\t4.查询账务\t\t5.备份账务数据库到本地（XML）");
            System.out.println("6.退出");
            System.out.println("请输入要操作的功能序号[1-6]:");

            //接收用户菜单选择
            int choose = scanner.nextInt();
            //对选择的菜单进行判断，调用不同的功能
            if (choose == 1){
                //1.添加账务，调用添加账务方法
                addZhangWu();
            }else if (choose == 2){
                //2.编辑账务，调用编辑账务方法
                editZhangWu();
            }else if (choose == 3){
                //3.删除账务，调用删除账务方法
                deleteZhangWu();
            }else if (choose == 4){
                //4.查询账务，调用查询账务方法
                selectZhangWu();
            }else if (choose == 5){
                //5.备份账务数据库到本地（XML），调用备份账务方法
                backupZhangWu();
            }else if (choose == 6){
                //6.退出系统
                System.exit(0);
                //break;
            }else {
                System.out.println("输入错误");
            }


        }
    }

    /**
     * 定义方法：备份账务
     *          调用controller层
     */
    public void backupZhangWu(){
        boolean flag = controller.backupZhangWu();
        if (flag){
            System.out.println("恭喜备份成功！！！");
        }else {
            System.out.println("备份失败");
        }
    }

    /**
     * 定义方法：删除账务功能，调用controller层
     */
    public void deleteZhangWu(){
        //首先查询全表
        selectAll();
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要删除的id编号，多个id用空格分开：");
        //接收多个id
        String allId = scanner.nextLine().trim();
        String[] ids = allId.split(" ");

        int rows = controller.deleteZhangWu(ids);

        if (rows < 0){
            System.out.println("删除失败");
        }else {
            System.out.printf("您成功删除了%d条数据%n",rows);
        }
    }

    /**
     * 定义方法：删除账务功能，调用controller层
     */
    public void editZhangWu(){
        //首先查询全表
        selectAll();

        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入要编辑的id编号：");
        int zwid = scanner.nextInt();

        //多余的字符串，解决nextInt（）后无法输入类别的问题，将Enter过滤掉
        String duoyu =  scanner.nextLine();

        System.out.print("请输入类别：");
        String flname = scanner.nextLine();

        System.out.print("请输入账户：");
        String zhanghu = scanner.nextLine();

        System.out.print("请输入金额：");
        double money = scanner.nextDouble();

        //多余的字符串，解决nextInt（）后无法输入类别的问题，将Enter过滤掉
        duoyu =  scanner.nextLine();

        String inputtime = "";

        while (!isDate(inputtime)){
            System.out.print("请输入日期，日期格式为XXXX-XX-XX：");
            inputtime = scanner.nextLine();
        }
        Date createtime = Date.valueOf(inputtime);

        System.out.print("请输入说明：");
        String description = scanner.nextLine();

        ////ZhangWu对象
        ZhangWu zhangWu = new ZhangWu(zwid,flname,money,zhanghu,createtime,description);
        int row = controller.editZhangWu(zhangWu);
        if (row != 1 ){
            System.out.println("修改失败...");
        }else {
            System.out.printf("成功修改%d条数据%n",row);
        }
    }


    /**
     * 定义方法：添加账务方法
     *          调用controller层方法
     */
    public void addZhangWu(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入类别：");
        String flname = scanner.nextLine();
        System.out.print("请输入账户：");
        String zhanghu = scanner.nextLine();
        System.out.print("请输入金额：");
        double money = scanner.nextDouble();

        //多余的字符串，解决nextInt（）后无法输入类别的问题，将Enter过滤掉
        String duoyu =  scanner.nextLine();

        String inputtime = "";

        while (!isDate(inputtime)){
            System.out.print("请输入日期，日期格式为XXXX-XX-XX：");
            inputtime = scanner.nextLine();
        }
        Date createtime = Date.valueOf(inputtime);

        System.out.print("请输入说明：");
        String description = scanner.nextLine();

        ////ZhangWu对象
        ZhangWu zhangWu = new ZhangWu(0,flname,money,zhanghu,createtime,description);
        int row = controller.addZhangWu(zhangWu);
        if (row != 1 ){
            System.out.println("添加失败...");
        }else {
            System.out.printf("成功添加%d条数据%n",row);
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
