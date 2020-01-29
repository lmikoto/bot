package io.github.lmikoto.bot.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String SIMPLE = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date){
        return new SimpleDateFormat(SIMPLE).format(date);
    }
}
