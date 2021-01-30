package edu.itstudy.service;

import edu.itstudy.model.Moive;
import edu.itstudy.util.ScannerUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class MoiveService {

    //保存影片的集合
    private List<Moive> moives = new ArrayList<>();
    //搜索后的影片集合
    private List<Moive> search_moives = new ArrayList<>();

    public List<Moive> getSearch_moives() {
        return search_moives;
    }

    //影片排序的类别
    //1.按名称排序	2.按上映时间排序	3.按类型排序	4.按点击率排序	5.按推荐次数排序	6.返回上一级
    private static final char SORT_BY_NAME = 1;  //按名字排序
    private static final char SORT_BY_DATE = 2;  //按上映时间排序
    private static final char SORT_BY_TYPE = 3;  //按类型排序
    private static final char SORT_BY_CLICKRATE = 4;    //按点击率排序
    private static final char SORT_BY_RECOMMENDRATE = 5;    //按推荐率排序

    //搜索条件 or 非搜索条件
    public  static final String SEARCH_CONDITION = "condition";
    public  static final String SEARCH_NO_CONDITION = "no_condition";


    {
       moives.add(new Moive(1231232,"囧途","喜剧","徐峥",new Date(),"徐峥",1,6));
       moives.add(new Moive(1231233,"人在囧途","喜剧","徐峥",new Date(),"徐峥",3,4));
       moives.add(new Moive(1231234,"nihao","爱情","sdsa",new Date(),"snsd",2,2));
       moives.add(new Moive(1231235,"张三丰","喜剧","张婷婷",new Date(),"张三",4,3));
       moives.add(new Moive(1231432,"李焕英","家庭","焦淑婷",new Date(),"包贝尔",6,1));
       moives.add(new Moive(3453232,"跑车家族","动作","沈腾",new Date(),"沈腾",5,5));
    }

    /**
     * 修改点击率
     * @param flag 是否是搜索过后对影片的点击率进行修改
     * @param index 需要修改的影片序号
     */
    public void updateClickRate(String flag, int index) {

        if (flag.equals(SEARCH_CONDITION)) {
            //修改search_moives这个集合
            Moive moive = this.search_moives.get(--index);
            moive.setClickRate(moive.getClickRate() + 1);
        }else if(flag.equals(SEARCH_NO_CONDITION)){
            //修改moives这个集合
            Moive moive = this.moives.get(--index);
            moive.setClickRate(moive.getClickRate() + 1);
        }
    }

    /**
     * 修改推荐率
     * @param flag 是否是搜索过后对影片的推荐率进行修改
     * @param index 需要修改的影片序号
     */
    public void updateRecommendRate(String flag, int index) {

        if (flag.equals(SEARCH_CONDITION)) {
            //修改search_moives这个集合
            Moive moive = this.search_moives.get(--index);
            moive.setRecommendRate(moive.getRecommendRate()+ 1);
        }else if(flag.equals(SEARCH_NO_CONDITION)){
            //修改moives这个集合
            Moive moive = this.moives.get(--index);
            moive.setRecommendRate(moive.getRecommendRate()+ 1);
        }
    }

    /**
     * 返回moives集合对象
     * @return
     */
    public List<Moive> getMoives() {
        return moives;
    }

    /**
     * 得到数据库中的影片数量
     * @return
     */
    public int getMoivesSize(){
        return moives.size();
    }

    /**
     * 添加影片
     * @param moive
     * @return true
     */
    public boolean addMoive(Moive moive){

        moives.add(moive);
        return true;

    }

    /**
     * 删除影片
     * @param index 需要删除的影片的序号
     * @return true
     */
    public boolean removeMoive(int index){

        Moive m = moives.remove(--index);
        if(m != null) {
            return true;
        }
        return false;
    }

    /**
     * 根据序号得到影片
     * @param index
     * @return
     */
    public Moive getMoiveByIndex(int index){
        if(moives.size() < index || index <= 0){

            return null;
        }
        return moives.get(--index);
    }

    /**
     * 根据指定属性进行排序（默认自然顺序）,返回排完序的影片集合
     * @param choose
     */
    public List<Moive> sort(char choose,List<Moive> list){
        if(choose == SORT_BY_NAME){
            //按名称排序
            //按照匿名内部类的方式
            Collections.sort(list, new Comparator<Moive>() {
                @Override
                public int compare(Moive moive, Moive t1) {
                    return moive.getName().compareTo(t1.getName());

                }
            });

        }else if(choose == SORT_BY_DATE){
            //按上映时间排序
            Collections.sort(list, new Comparator<Moive>() {
                @Override
                public int compare(Moive moive, Moive t1) {
                    return moive.getDate().compareTo(t1.getDate());
                }
            });

        }else if(choose == SORT_BY_TYPE){
            //按类型排序
            Collections.sort(list, new Comparator<Moive>() {
                @Override
                public int compare(Moive moive, Moive t1) {
                    return moive.getType().compareTo(t1.getType());
                }
            });

        }else if(choose == SORT_BY_CLICKRATE){
            //按点击率排序
            Collections.sort(list, new Comparator<Moive>() {
                @Override
                public int compare(Moive moive, Moive t1) {
                    return moive.getClickRate()-t1.getClickRate();
                }
            });

        }else if (choose == SORT_BY_RECOMMENDRATE){
            //按推荐率排序
            Collections.sort(list, new Comparator<Moive>() {
                @Override
                public int compare(Moive moive, Moive t1) {
                    return moive.getRecommendRate()-t1.getRecommendRate();
                }
            }

            );

        }
        return list;
    }


    /**
     * 根据序号的新修改完的对象替换掉原来的对象
     * @param index 需要替换的对象的序号
     * @param moive 新修改的电影对象
     * @return true
     */
    public boolean updateMoiveByIndexAndObj(int index,Moive moive){
        if(moives.set(index,moive)!= null){
            return true;
        }
        return false;
    }

    /**
     * 根据三个条件查询影片
     * @param name
     * @param type
     * @param performer
     * @return
     */
    public List<Moive> searchMoive(String name,String type,String performer)throws NumberFormatException{
        //模糊查询
        //判断字符串是否为空 判断一个字符串是否包含一个字符串

        for (Moive m : moives){
            String name1 = m.getName();
            String type1 = m.getType();
            String performer1 = m.getPerformer();
            if((null == name ? true:name1.contains(name))  &&  (null == type ? true:type1.contains(type))
                    && (null == performer ? true:performer1.contains(performer)) ){
                search_moives.add(m);
            }
        }


        return search_moives;
    }


}
