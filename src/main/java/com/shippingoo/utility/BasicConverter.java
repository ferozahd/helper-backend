package com.shippingoo.utility;

import org.mapstruct.Named;

import java.time.LocalDateTime;

@Named("BasicConverter")
public class BasicConverter {
    @Named("createdAtToyyyuMMdd")
    public static String createdAtToyyyMMdd(LocalDateTime localDateTime) {
        StringBuilder sb = new StringBuilder();

        sb.append(localDateTime.getYear());
        String month = localDateTime.getMonth().toString().substring(0, 3);
        sb.append(" " + month + ", ");
        sb.append(localDateTime.getDayOfMonth());

        return sb.toString();
    }
    @Named("yyMMddThhMMss")
    public static String yyMMddThhMMss(LocalDateTime localDateTime){
        StringBuilder sb = new StringBuilder();

        sb.append(localDateTime.getYear());
        sb.append(" "+localDateTime.getMonthValue());
        sb.append(" "+localDateTime.getDayOfMonth());

        sb.append("-"+localDateTime.getHour());
        sb.append(":"+localDateTime.getMinute());
        sb.append(":"+localDateTime.getSecond());

        return  sb.toString();
    }
}
