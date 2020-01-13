package io.github.lmikoto.bot.api.xz.model;

import lombok.Data;

@Data
public class Now{
    //天气现象文字
    private String text;
    //天气现象代码
    private String code;
    //温度，单位为c摄氏度或f华氏度
    private String temperature;
    //体感温度，单位为c摄氏度或f华氏度
    private String feels_like;
    //气压，单位为mb百帕或in英寸
    private String pressure;
    //相对湿度，0~100，单位为百分比
    private String humidity;
    //能见度，单位为km公里或mi英里
    private String visibility;
    //风向文字
    private String wind_direction;
    //风向角度，范围0~360，0为正北，90为正东，180为正南，270为正西
    private String wind_direction_degree;
    //风速，单位为km/h公里每小时或mph英里每小时
    private String wind_speed;
    //风力等级，请参考：http://baike.baidu.com/view/465076.htm
    private String wind_scale;
    //云量，单位%，范围0~100，天空被云覆盖的百分比 #目前不支持中国城市#
    private String clouds;
    //露点温度，请参考：http://baike.baidu.com/view/118348.htm #目前不支持中国城市#
    private String dew_point;
}