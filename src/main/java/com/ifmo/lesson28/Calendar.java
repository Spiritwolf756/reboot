package com.ifmo.lesson28;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class Calendar {
    public static void main(String[] args) throws InterruptedException {
        //findFridays();
        ny();;
    }

    //сколько пятниц в году
    private static void findFridays() {
        Date date = new Date(2019, java.util.Calendar.JANUARY, 0);
        Date end = new Date(2019, java.util.Calendar.DECEMBER, 31);
        while (date.before(end)) {
            String d = new SimpleDateFormat("d").format(date);
            String a = new SimpleDateFormat("E").format(date);

            if ("13".equals(d) && "пн".equals(a))
                System.out.println(date);
            date = new Date(date.getTime() + 86400000);
        }
    }

    //сколько осталось до НГ
    private static void ny() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.of(2019, java.util.Calendar.DECEMBER, 31, 23, 59, 59);
        Duration duration = Duration.between(end, now);

    }

    //сколько секунд прожил
    private static void howLongAlive() {

    }

}
