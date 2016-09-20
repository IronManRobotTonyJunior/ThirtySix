package com.example.dllo.thirtysixkr.news;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatTime {
    private static long timeMillis;
    static String returnTime;
    static long subtractTime;

    public static String formatTime(String mTime) {
        timeMillis = Calendar.getInstance().getTimeInMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String nowTimeDay = format.format(timeMillis).substring(8, 10);
        String inTimeDay = format.format(Long.parseLong(mTime)).substring(8, 10);
        String inTime = format.format(Long.parseLong(mTime));
        switch (Integer.parseInt(nowTimeDay) - Integer.parseInt(inTimeDay)) {
            case 0:
                // 今天的时间判断是否大于一小时
                subtractTime = (timeMillis - Long.parseLong(mTime)) / 60;
                if (subtractTime < 60) {
                    returnTime = subtractTime + "分钟前";
                }
                else {
                    returnTime = inTime.substring(11,16);
                }
                break;
            case 1:
                returnTime = "昨天";
                break;
            case 2:
                returnTime = "前天";
                break;
            default:
                returnTime = inTime.substring(5,10);
                break;
        }
        return returnTime;

    }
}
