package edu.itstudy;

import edu.itstudy.view.MoiveView;

/**
 * @author zhangpeishi
 * @version 1.0
 */
public class MoiveStart {

    public static void main(String[] args) {

        System.out.println("=================欢迎使用影片管理系统===============");

        MoiveView mv = new MoiveView();

        mv.mainMenu();

    }
}
