package com.taller.selenium.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FechaHelper {

    public static String fechaManiana(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now.plusDays(1));
    }

}
