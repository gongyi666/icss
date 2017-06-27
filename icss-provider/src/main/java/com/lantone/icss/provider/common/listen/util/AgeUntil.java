package com.lantone.icss.provider.common.listen.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description: 周岁计算工具
 * @author: gaodaming
 * @date: 2017/6/4 13:529
 * @version: V1.0
 */
public class AgeUntil {

    //由出生日期获得年龄
    public  static int getAge(Date dateOfBirth){
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                return 0;
                //throw new IllegalArgumentException("年龄不能超过当前日期");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            int nowDayOfYear = now.get(Calendar.DAY_OF_YEAR);
            int bornDayOfYear = born.get(Calendar.DAY_OF_YEAR);
//            System.out.println("nowDayOfYear:" + nowDayOfYear + " bornDayOfYear:" + bornDayOfYear);
            if (nowDayOfYear < bornDayOfYear) {
                age -= 1;
            }
        }
        return age;
    }
}
