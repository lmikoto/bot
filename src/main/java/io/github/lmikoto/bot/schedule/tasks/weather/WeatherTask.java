package io.github.lmikoto.bot.schedule.tasks.weather;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.api.xz.XZApi;
import io.github.lmikoto.bot.api.xz.model.Daily;
import io.github.lmikoto.bot.api.xz.response.XZDailyResponse;
import io.github.lmikoto.bot.schedule.service.Task;
import org.springframework.stereotype.Component;

@Component
public class WeatherTask implements Task {

    @Override
    public String getNoticeMsg(String param) {
        WeatherTaskParam weatherTaskParam = JacksonUtils.fromJson(param,WeatherTaskParam.class);
        XZDailyResponse response = XZApi.getDailyWeather(weatherTaskParam.getLocation());
        Daily daily = response.getResults().get(0).getDaily().get(0);
        StringBuffer stringBuffer = new StringBuffer()
                .append(weatherTaskParam.getLocation())
                .append(" 白天: ")
                .append(daily.getText_day())
                .append(" 晚间: ")
                .append(daily.getText_night())
                .append(" ")
                .append(daily.getHigh())
                .append("℃-")
                .append(daily.getLow())
                .append("℃");
        return stringBuffer.toString();
    }

}
