package io.github.lmikoto.bot.weather;

import com.google.common.collect.Maps;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.schedule.service.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WeatherTask implements Task {

    @Value("${xz.key}")
    private String xzKey;

    public static final String XZ_API_PATH = "https://api.seniverse.com/v3/weather/now.json";

    @Override
    public String getNoticeMsg(String param) {
        WeatherTaskParam weatherTaskParam = JacksonUtils.fromJson(param,WeatherTaskParam.class);
        XZResponse response = getWeather(weatherTaskParam.getLocation());
        return JacksonUtils.toJson(response);
    }

    private XZResponse getWeather(String location){
        Map<String,String> param = Maps.newHashMap();
        param.put("key",xzKey);
        param.put("location",location);
        param.put("language","zh-Hans");
        param.put("unit","c");
        return JacksonUtils.fromJson( HttpUtils.get(XZ_API_PATH,param),XZResponse.class);
    }
}
