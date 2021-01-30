package edu.itstudy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public  class ScannerUtil {


    private static Scanner sc = new Scanner(System.in);

    /**
     * 读取指定长度的整数
     * @return int
     */

    public static int readInt() {
        int i = 0;
        while (true) {

            try {
                i = Integer.parseInt(readKeyBoard(2, true));
            } catch (Exception e) {
                System.out.println("格式错误！");
                continue;
            }
            break;
        }

        return i;
    }

    /**
     * 从键盘得到输入 y/n
     * @return char
     */
    public static char readConfirmSelect() {
        String str = " ";
        while (true) {
            str = readKeyBoard(1, false).toUpperCase();
            char c = str.charAt(0);
            if ('Y' != c && 'N' != c) {
                System.out.println("请输入y/n！");
                continue;
            }
            return c;
        }
    }

    /**
     * 获取一个随机数，不会重复，作为电影ID
     * @return
     */
    public static int getRandomInt(){
        long time = System.currentTimeMillis();
        String temp = (time+"").substring(7);
        Random r = new Random();
        int i = r.nextInt(100);
        int randomint = Integer.parseInt(temp+i);
        return randomint ;
    }

    /**
     * 读入一个指定格式的日期类型
     * @param pattern 指定日期类型
     * @return Date
     */
    public static Date readDate(String pattern){
        Date date = null;
        //2020-02-02
        while(true){
            String str = readKeyBoard(pattern.length(),false);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                System.out.println("输入格式有误！请重新输入！");
                continue;
            }
            break;

        }
        return date;

    }

    /**
     * 从控制台读取一个指定格式的日期类型
     * @param pattern 指定格式的字符串
     * @param defaultValue 无输入敲回车时的返回值
     * @return Date
     */
    public static Date readDate(String pattern,Date defaultValue){
        Date date = null;
        //2020-02-02
        while(true){
            String str = readKeyBoard(pattern.length(),false);
            if("".equals(str)){
                return defaultValue;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                date = sdf.parse(str);
            } catch (ParseException e) {
                System.out.println("输入格式有误！请重新输入！");
                continue;
            }
            break;

        }
        return date;

    }

    /**
     * 从控制台读入一个字符串
     * @param limit 指定最大长度
     * @return
     */
    public static String readString(int limit){
        return readKeyBoard(limit,false);
    }

    /**
     * 从控制台读入一个字符串，为空则返回默认值
     * @param limit 指定的最大长度
     * @param defaultValue 默认值
     * @return String
     */
    public static String readString(int limit, String defaultValue){

        String str = readKeyBoard(limit,true);

        return "".equals(str)? defaultValue:str;
    }

    /**
     * 从控制台输入选择的值
     * @return char
     */
    public static char readMenuSelect(int size) {
        char c = ' ';
        boolean r  = true;
        while (r) {

            String str = readKeyBoard(1, false);
            c = str.charAt(0);


            for (int i = 1; i <=  size; i++) {
                if (i+'0' == c) {
                    r = false;
                    break;
                }
            }
            if(r){
                System.out.println("输入错误！请重新输入！");
            }

        }
        return c;

    }

    /**
     * 从控制台按照指定要求获取输入的一行数据
     * @param limit 输入的字符最大数量
     * @param blankReturn 是否可以不输入直接回车
     * @return String,返回输入的一行字符串
     */
    private static String readKeyBoard(int limit , boolean blankReturn){
        String line = "";
        while(true){
            line = sc.nextLine();
            line = line.trim(); //对字符串处理，去掉前后空格
            if(line.length() == 0){
                if(blankReturn){    //判断此时是否允许不输入
                    return line;
                }else {
                    continue;
                }
            }
            if(line.length() > limit){  //判断输入长度是否超过限制
                System.out.println("输入字符长度大于"+limit+"，请重新输入！");
                continue;
            }
            break;
        }
        return line;


    }


}
