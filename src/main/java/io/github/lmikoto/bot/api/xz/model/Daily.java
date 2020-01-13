package io.github.lmikoto.bot.api.xz.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Daily implements Serializable {
    private static final long serialVersionUID = -1949016872970088610L;

    //日期
    private String date;

    //白天天气现象文字
    private String text_day;

    //白天天气现象代码
    private String code_day;

    //晚间天气现象文字
    private String text_night;

    //晚间天气现象代码
    private String code_night;

    //当天最高温度
    private String high;

    //当天最低温度
    private String low;

    //降水概率，范围0~100，单位百分比（目前仅支持国外城市）
    private String precip;

    //风向文字
    private String wind_direction;

    //风向角度，范围0~360
    private String wind_direction_degree;

    //风速，单位km/h（当unit=c时）、mph（当unit=f时）
    private String wind_speed;

    //风力等级
    private String wind_scale;

    private String humidity;

}
