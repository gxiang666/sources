package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {

    public static void main(String[] args) {
        //关于日期的格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(new Date());
        System.out.println(format);
        //关于日期计算
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        System.out.println(c.getTime());
        String format1 = sdf.format(c.getTime());
        System.out.println(format1);
    }

    public static String getMyTime(String format) {
        //关于日期的格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(new Date());
        return str;
    }

    public static Date getMyDate(int i) {

        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, -1);

        return c.getTime();
    }

}
