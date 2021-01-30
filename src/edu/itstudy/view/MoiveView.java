package edu.itstudy.view;

import edu.itstudy.model.Moive;
import edu.itstudy.service.MoiveService;
import edu.itstudy.util.ScannerUtil;


import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class MoiveView {

    private MoiveService ms = new MoiveService();
    private Scanner sc = new Scanner(System.in);
    public void mainMenu(){
        while(true) {

            System.out.println("1.影片管理\t2.查看所有影片\t3.退出系统");
            System.out.println("请选择序号：");
            //定义一个方法直接获取输入的123
            char choose = ScannerUtil.readMenuSelect(3);

            choose1_first(choose);
        }
    }

    /**
     * 一级选择菜单：影片管理，查看所有影片，退出系统
     * @param choose
     */
    public void choose1_first(char choose){
        switch (choose){
            case '1':
                System.out.println("----影片管理----");
                System.out.println("1.上传影片\t2.删除影片\t3.修改影片\t4.返回上一级");
                System.out.println("请选择序号：");
                char c = ScannerUtil.readMenuSelect(4);

                choose11_manageMoive(c);

                break;
            case '2':
                System.out.println("----查看所有影片----");
                printMoives(ms.getMoives());
                //查看当前数据库中影片数量
                if(ms.getMoivesSize() == 0){
                    System.out.println("数据库中无影片！");
                    return;
                }
                System.out.println("1.按条件查询影片\t2.影片排序\t3.返回上一级");
                System.out.println("请选择序号：");
                char c1 = ScannerUtil.readMenuSelect(3);

                choose12_selectMoive(c1);

                break;
            default:
                System.out.println("----退出系统----");
                System.exit(0);
        }

        }

    /**
     * 电影管理菜单：增删改查
     * @param choose
     */
    public void choose11_manageMoive(char choose){
        switch (choose){
            case '1':
                //上传电影
                System.out.println("请输入电影的相关属性");
                Moive moive = this.addMoiveObj();
                ms.addMoive(moive);
                System.out.println("==============影片上传成功！=================");
                System.out.println();
                break;
            case '2':
                //删除电影
                printMoives(ms.getMoives());
                //查看当前数据库中影片数量
                if(ms.getMoivesSize() == 0){
                    System.out.println("数据库中无影片！");
                    return;
                }
                System.out.println("请输入要删除的影片的序号：");
                int index  = ScannerUtil.readInt();
                //Moive m =readIndex(index);
                boolean r = ms.removeMoive(index);
                if(r){
                    System.out.println("影片删除成功！");
                }
                System.out.println();
                break;
            case '3':
                //修改电影
                printMoives(ms.getMoives());
                //查看当前数据库中影片数量
                if(ms.getMoivesSize() == 0){
                    System.out.println("数据库中无影片！");
                    return;
                }
                System.out.println("请输入要修改的影片的序号：");
                int index1 = ScannerUtil.readInt();
                Moive moive1 = readIndex(index1);
                System.out.println("请输入要修改的属性值：");
                Moive moive_update = updateMoive(moive1);
                boolean r1 = ms.updateMoiveByIndexAndObj(--index1,moive_update);
                if(r1){
                    System.out.println("影片修改成功！");
                }
                break;
            default:
                //返回上一级
                break;
        }
    }
    /**
     * 电影搜索或电影排序菜单
     * @param choose
     */
    private void choose12_selectMoive(char choose) throws NullPointerException{
        switch (choose){
            case '1':
                List<Moive> list = choose12_search();
                if(list == null){
                    break;
                }
                System.out.println("1.影片排序2.观看影片3.推荐影片4.返回影院系统5.退出系统");
                System.out.println("请选择序号：");
                char c = ScannerUtil.readMenuSelect(5);
                choose121(c,list);

                break;
            case '2':
                //未经搜索的影片排序
                System.out.println("请选择排序条件：1.按名称排序\t2.按上映时间排序\t3.按类型排序\t4.按点击率排序\t5.按推荐次数排序\t6.返回上一级");
                char c1  = ScannerUtil.readMenuSelect(6);
                if(c1 == '6'){
                    return;
                }
                ms.sort(c1,ms.getMoives());
                printMoives(ms.getMoives());

                printMoives(ms.getMoives());
                //排序完之后提示 观看或推荐 或者退出系统
                System.out.println("1.观看影片2.推荐影片3.返回影院系统4.退出系统");
                System.out.println("请选择序号：");
                char c2 = ScannerUtil.readMenuSelect(4);
                choose122(c2);
                break;
            default:
                break;
        }

    }

    /**
     * 未搜索进行排序后的选择菜单（观看影片，推荐影片，返回影院系统，退出系统）此时操作的集合是moives
     * @param choose
     */

    public void choose122(char choose){
        switch (choose){
            case '1':
                //观看电影
                watchMoive(MoiveService.SEARCH_NO_CONDITION);
                break;
            case '2':
                //推荐电影
                recommendMoive(MoiveService.SEARCH_NO_CONDITION);
                break;
            case '3':
                break;
            default:
                System.exit(0);
        }

    }

    /**
     * 搜索后，影片排序，观看，推荐等菜单 此时操作的集合是search_moives
     * @param choose
     * @param list
     */
    public void choose121(char choose,List<Moive> list){
        switch (choose){
            case'1':
                //影片进行排序
                System.out.println("请选择排序条件：1.按名称排序\t2.按上映时间排序\t3.按类型排序\t4.按点击率排序\t5.按推荐次数排序\t6.返回上一级");
                char c  = ScannerUtil.readMenuSelect(6);
                if(c == '6'){
                    return;
                }
                ms.sort(c,ms.getSearch_moives());
                printMoives(ms.getMoives());
                break;
            case '2':
                //观看影片
                watchMoive(MoiveService.SEARCH_CONDITION);
            case'3':
                //推荐影片
                recommendMoive(MoiveService.SEARCH_CONDITION);
            case '4':
                break;
            default:
                System.exit(0);
        }
    }



    /**
     * 观看电影
     * @param flag 标识符，观看电影是从搜索后观看还是直接观看，影响到修改moives的clickreate还是search_moives的
     */
    public void watchMoive(String flag){
        System.out.println("请选择要观看的电影序号：");
        int index = ScannerUtil.readInt();
        Moive moive = readIndex(index);
        //修改影片点击率
        ms.updateClickRate(flag,index);
        System.out.println("=====================张赔十导的电影太好看了！==================================");

    }

    /**
     * 推荐电影
     * @param flag 标识符，观看电影是从搜索后观看还是直接观看，影响到修改moives的clickreate还是search_moives的
     */
    public void recommendMoive(String flag){
        System.out.println("请选择要推荐的电影序号：");
        int index = ScannerUtil.readInt();
        Moive moive = readIndex(index);
        System.out.println("确认推荐 "+moive.getName()+" 影片吗?(y/n)");
        char c  = ScannerUtil.readConfirmSelect();
        if('Y' == c) {
            //修改影片推荐率
            ms.updateRecommendRate(flag, index);
            System.out.println("=================喜欢"+ moive.getName() +"太有眼光了！推荐成功！=======================");
        }
        else{
            System.out.println("取消推荐！");
            return;
        }
    }
    /**
     * 影片搜索
     * @return List<Moive>
     * @throws NumberFormatException
     */
    public List<Moive>  choose12_search() throws NumberFormatException{
        System.out.println("请输入搜索条件");
        System.out.println("影片名称：");
        String name = ScannerUtil.readString(5,null);
        System.out.println("影片类型：");
        String type = ScannerUtil.readString(5,null);
        System.out.println("影片主演：");
        String performer = ScannerUtil.readString(5,null);

        System.out.println("您输入的搜索条件为：");
        if(name != null){
            System.out.println("\t影片名称：" + name);
        }
        if(type != null){
            System.out.println("\t影片类型：" + type);
        }
        if(performer != null){
            System.out.println("\t影片主演：" + performer);
        }
        if(name == null &&  type == null && performer == null){
            System.out.println("\t您没有输入搜索条件！");
        }
        System.out.println("1.搜索2.返回上一级");
        char c = ScannerUtil.readMenuSelect(2);
        List<Moive> search_list = null;
        if('1' == c){
            //得到搜索后的影片集合
            search_list = ms.searchMoive(name,type,performer);

            printMoives(search_list);
        }else {
            return null;
        }
        return search_list;

    }

    /**
     * 通过序号获取电影
     * @param
     * @return
     */
    private Moive readIndex(int index){

        Moive moive = null;
        while (true) {
            moive = ms.getMoiveByIndex(index);
            if(moive == null){
                System.out.println("影片不存在，请重新输入！");

                continue;
            }
            break;
        }
        return moive;
    }

    /**
     * 修改影片,得到新的影片对象
     * @param moive
     * @return Moive
     */

    private Moive updateMoive(Moive moive) {

        System.out.println("影片名称："+moive.getName());
        String name = ScannerUtil.readString(5,moive.getName());

        System.out.println("影片类型：");
        String type = ScannerUtil.readString(5,moive.getType());

        System.out.println("影片主演：");
        String performer = ScannerUtil.readString(5,moive.getPerformer());

        System.out.println("影片上映时间(yyyy-mm-dd)：");
        Date date = ScannerUtil.readDate("yyyy-mm-dd",moive.getDate());

        System.out.println("影片导演：");
        String guider = ScannerUtil.readString(5,moive.getGuider());


        Moive moive1 = new Moive(moive.getMid(),name,type,performer,date,guider,moive.getClickRate(),moive.getRecommendRate());

        return moive1;
    }

    /**
     * 获得需要添加的Moive对象，然后添加电影
     * @return Moive
     */
    private  Moive addMoiveObj(){

        System.out.println("影片名称：");
        String name = ScannerUtil.readString(5);

        System.out.println("影片类型：");
        String type = ScannerUtil.readString(5);

        System.out.println("影片主演：");
        String performer = ScannerUtil.readString(5);

        System.out.println("影片上映时间(yyyy-mm-dd)：");
        Date date = ScannerUtil.readDate("yyyy-mm-dd");

        System.out.println("影片导演：");
        String guider = ScannerUtil.readString(5);
        //生成一个影片ID
        int mid = ScannerUtil.getRandomInt();
        //new一个Moive对象接受输入的条件
        Moive moive = new Moive(mid,name,type,performer,date,guider,0,0);

        return moive;
    }
    /**
     * 遍历所有电影
     */
    private void printMoives(List<Moive> moives){
        System.out.println("================影片列表=================");
        System.out.println("Index/ID\t\t名称\t类型\t主演\t上映日期\t\t\t\t\t\t\t导演\t点击率\t推荐率");
        System.out.println("-------------------------------------------------------------------");

        int i = 0;
        for(Moive moive : moives){
            i ++;
            System.out.println(i+"/"+moive);

        }
    }


}