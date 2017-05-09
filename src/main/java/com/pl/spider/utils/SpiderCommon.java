package com.pl.spider.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luweineng on 2016/8/16.
 */
public class SpiderCommon {

    public static String getFileType(String fileName) {
        if (!StringUtils.isNullOrEmpty(fileName)) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    public static String getRegexWord(String content, String regexStr) {
        String key = "";
        Pattern p = Pattern.compile(regexStr);
        Matcher m = p.matcher(content);
        if (m.find()) {
            key = m.group(1);
        }
        return key;
    }

    public static List<String> getListByRegex(String content, String regexStr) {
        List<String> result = new ArrayList<String>();
        Pattern p = Pattern.compile(regexStr);
        Matcher m = p.matcher(content);
        while (m.find()) {
            int count = m.groupCount();

            for (int i = 0; i < count; i++) {
                String key = m.group(i);
                if (!StringUtils.isNullOrEmpty(key)) {
                    result.add(key);
                }
            }

        }
        return result;
    }

    public static Date fromString(String str, String format) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        return df.parse(str);
    }

    public static Date fromString(String str, String format,Locale locale) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format, locale);
        return df.parse(str);
    }

    public static Date fromString(String str, String format, String timezone) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone(timezone));
        return df.parse(str);
    }

    public static boolean isImg(String url) {
        if (!StringUtils.isNullOrEmpty(url)) {
            Pattern p = Pattern.compile("\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\.bin)\\b)", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    public static String getFullUrl(String url, String src) {
        if (!StringUtils.isNullOrEmpty(src)) {
            if (src.startsWith("http")) {
                return src;
            }
            Pattern p = Pattern.compile(".*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
            Matcher matcherSrc = p.matcher(src);
            if (matcherSrc.find()) {
                return src;
            } else {
                Matcher matcherUrl = p.matcher(url);
                if (matcherUrl.find()) {
                    src = src.startsWith("/") ? src : "/" + src;
                    return matcherUrl.group() + src;
                }
            }
        }
        return src;
    }


    public static String getSub(String rawStr, String subStr) {
        if (!StringUtils.isNullOrEmpty(rawStr) && rawStr.indexOf(subStr) >= 0) {
            return rawStr.substring(0, rawStr.indexOf(subStr));
        }
        return rawStr;
    }

    public static String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    public static void main(String[] args) throws ParseException {
//        System.out.println(getRegexWord("Celebrity Beauty Looks We Love 27/0w6/16","(\\d{2}/\\d{2}/\\d{2})"));
        System.out.println(getFullUrl("http://www.news18.com/", "http://img01.ibnlive.in/ibnlive/uploads/281x187/jpg/2016/09/jon-voight.jpg"));
    }
}
