package com.announceMe.util;

import java.time.format.DateTimeFormatter;

public class DateUtil {
    public DateTimeFormatter dateTimeFormatter(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }
}
