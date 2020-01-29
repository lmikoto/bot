package io.github.lmikoto.bot.message.deal.remind;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum  TimeEnums {

    SECOND("秒",1000),
    MINUTE("分钟",SECOND.millisecond * 60),
    HOUR("小时",MINUTE.millisecond * 60),
    DAY("天",HOUR.millisecond * 24);

    private String desc;
    private int millisecond;
}
