package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getHMS (Long mills){
        return "("+String.valueOf(mills/3600000)+"h "+String.valueOf((mills % 3600000) / 60000)+"m "+(mills % 60000 ) / 1000+"s前)";
    }

    public static String getHHmmss(String mills){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(Long.parseLong(mills)).substring(11);
    }

    public static int getNowMills()throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(System.currentTimeMillis());
        return (int)(System.currentTimeMillis()-format.parse(d+" 00:00:00").getTime());
    }

    public static String getDateByNowMills(int mills)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(format.parse(new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())+" 00:00:00").getTime()+mills).substring(11);
    }

    //获取某个是时间对应的今日的秒数
    public static int getTodayMills(Long mills)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(mills);
        return (int)(mills-format.parse(d+" 00:00:00").getTime())%86400000;
    }


    public static Long getTodayZeroMills(String mills)throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(Long.parseLong(mills));
        return format.parse(d+" 00:00:00").getTime();
    }

    public static Long getTomorrowMills(Long time)throws Exception{

        //获取时间戳
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(time);
        return format.parse(d+" 00:00:00").getTime()-86400000l;
    }
    public static String getNowDate (Object mills){
        Long millss ;
        if (mills instanceof String)
            millss = Long.parseLong((String)mills);
        else
            millss=(Long)mills;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(millss);
    }
    public static Object[] timeArray2DateArray (Object[] millArray){
        for (int i = 0; i < millArray.length; i++) {
            millArray[i]=getNowDate(millArray[i]);
        }
        return millArray;
    }
}
