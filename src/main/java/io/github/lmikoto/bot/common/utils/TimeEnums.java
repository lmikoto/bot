package io.github.lmikoto.bot.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public enum  TimeEnums {

    SECOND("秒",1000,"每秒"),
    MINUTE("分钟",SECOND.millisecond * 60,"每分种"),
    MINUTE_SIMPLE("分",SECOND.millisecond * 60,"每分"),
    HOUR("小时",MINUTE.millisecond * 60,"每小时"),
    DAY("天",HOUR.millisecond * 24,"每天"),
    WEEK("周",DAY.millisecond * 7,"每周");

    @Getter
    private String desc;

    @Getter
    private int millisecond;

    @Getter
    private String everyDesc;

    public static TimeEnums get(String desc){
        for(TimeEnums timeEnums: TimeEnums.values()){
            if(Objects.equals(timeEnums.desc,desc)){
                return timeEnums;
            }
        }
        return null;
    }

    public static TimeEnums contain(String time){
        for(TimeEnums timeEnums: TimeEnums.values()){
            if(time.contains(timeEnums.desc)){
                return timeEnums;
            }
        }
        return null;
    }
}
