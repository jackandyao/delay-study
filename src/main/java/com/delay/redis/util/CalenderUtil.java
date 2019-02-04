package com.delay.redis.util;

import java.util.Calendar;

/**
 * @ 创建人 贾红平
 * @ 创建时间 2019/2/3
 * @ 功能描述
 */
public class CalenderUtil {
    public static String getCurrentTimeByStr(int second){
        Calendar calendar = Calendar.getInstance();
        if (second>0){
            calendar.add(Calendar.SECOND,second);
        }

        String time = calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+
                ":"+calendar.get(Calendar.SECOND);
        return time;
    }

    public static long getCurrentTimeInMills(int second){
        Calendar calendar = Calendar.getInstance();
        if (second>0){
            calendar.add(Calendar.SECOND,second);
        }
        return calendar.getTimeInMillis();
    }
}
