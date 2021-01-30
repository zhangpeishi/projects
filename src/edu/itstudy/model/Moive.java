package edu.itstudy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Moive {

    //ID 名称 类型 主演 上映时间 导演 推荐率 点击率
    private int mid;
    private String name;
    private String type;
    private String performer;  //电影主演
    private Date date;         //上映时间
    private String guider;     //电影导演
    private int clickRate;     //点击率，点击后加一
    private  int recommendRate; //推荐率，推荐后加一

    public  Moive(){

    }
    public Moive(int mid, String name, String type, String performer, Date date, String guider, int clickRate, int recommendRate) {
        this.mid = mid;
        this.name = name;
        this.type = type;
        this.performer = performer;
        this.date = date;
        this.guider = guider;
        this.clickRate = clickRate;
        this.recommendRate = recommendRate;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGuider() {
        return guider;
    }

    public void setGuider(String guider) {
        this.guider = guider;
    }

    public int getClickRate() {
        return clickRate;
    }

    public void setClickRate(int clickRate) {
        this.clickRate = clickRate;
    }

    public int getRecommendRate() {
        return recommendRate;
    }

    public void setRecommendRate(int recommendRate) {
        this.recommendRate = recommendRate;
    }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");

        return
                mid + "\t"+name +"\t" + type +"\t" + performer+"\t" +sdf.format(date) +"\t" +guider + "\t"  +clickRate  + "\t" +"\t"+recommendRate ;
    }
}
