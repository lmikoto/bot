package io.github.lmikoto.bot.api.xz;

import com.google.common.collect.Maps;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.api.xz.response.XZCurrentWeatherResponse;
import io.github.lmikoto.bot.api.xz.response.XZDailyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class XZApi  {

    public static String XZ_KEY;

    public static final String XZ_API_PATH = "https://api.seniverse.com/v3/weather";

    @Autowired
    public void init(@Value("${xz.key}") String xzKey){
        XZ_KEY = xzKey;
    }

    public static XZCurrentWeatherResponse getCurrentWeather(String location){
        Map<String,String> param = Maps.newHashMap();
        param.put("key",XZ_KEY);
        param.put("location",location);
        param.put("language","zh-Hans");
        param.put("unit","c");
        return JacksonUtils.fromJson(HttpUtils.get(XZ_API_PATH + "/now.json",param), XZCurrentWeatherResponse.class);
    }

    public static XZDailyResponse getDailyWeather(String location){
        Map<String,String> param = Maps.newHashMap();
        param.put("key",XZ_KEY);
        param.put("location",location);
        param.put("language","zh-Hans");
        param.put("unit","c");
        param.put("start","0");
        param.put("days","2");
        System.out.println(HttpUtils.get(XZ_API_PATH + "/daily.json",param));
        return JacksonUtils.fromJson(HttpUtils.get(XZ_API_PATH + "/daily.json",param), XZDailyResponse.class);
    }
}
