package com.pl.spider.utils;

/**
 * Created by Administrator on 2017/4/22.
 */
public class StringUtils {
    public static boolean isNullOrEmpty(String str){
        if("".equals(str)||null ==str){
            return true;
        }
        return false;
    }
}
