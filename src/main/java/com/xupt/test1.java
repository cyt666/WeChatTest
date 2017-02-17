package com.xupt;

/**
 * Created by as on 2017/2/10.
 */
public class test1 {
    public static void main(String[] args) {
        String s = null;
        if ((s!=null)&(s.length()>0)){
            System.out.println(1);
        }
        if ((s!=null)&&(s.length()>0)){
            System.out.println(2);
        }
        if ((s!=null)|(s.length()>0)){
            System.out.println(3);
        }
        if ((s!=null)||(s.length()>0)){
            System.out.println(4);
        }
    }
}
