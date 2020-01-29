package io.github.lmikoto.bot.message.deal.remind;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public enum  TimeEnums {

    SECOND("秒",1000),
    MINUTE("分钟",SECOND.millisecond * 60),
    MINUTE_SIMPLE("分",SECOND.millisecond * 60),
    HOUR("小时",MINUTE.millisecond * 60),
    DAY("天",HOUR.millisecond * 24);

    @Getter
    private String desc;

    @Getter
    private int millisecond;

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
