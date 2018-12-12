package com.example.other;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author: qiudong
 * @description:
 * @date: Created in 12:50 2018-05-24
 */
public class TimeTest {

    public static void main(String[] args){
//        try {
//            Long now = 1514908800000L;
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            String ymd = format.format(new Date(now));
//            System.out.println("-------"+ymd);
//
//            String time = "11:24";
//            String date = StringUtils.join(ymd, " ", time);
//            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//
//            Date fromTime = format1.parse(date);
//            Long value = fromTime.getTime();
//            System.out.println("------value:"+value);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Long value = System.currentTimeMillis()-100;
//        System.out.println("---------"+value);
//        Boolean b = isTimeout(value, 1L);
//        System.out.println("-------"+b);


        System.out.println("---------"+getRemainSecondsOneDay(new Date()));

    }

    private static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault()).plusDays(1).withHour(0).withMinute(0)
                .withSecond(0).withNano(0);
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }


    /**
     *
     * @param time 时间戳
     * @param scope 秒
     * @return
     */
    private static Boolean isTimeout(Long time, Long scope) {
        Long currentTime = System.currentTimeMillis();
        return currentTime >= time && currentTime-time >= (long)(scope.intValue()*1000);
    }
}
