package io.github.lmikoto.bot.weather;

import io.github.lmikoto.bot.schedule.dto.BaseTaskParam;
import lombok.Data;

@Data
public class WeatherTaskParam extends BaseTaskParam {

    private String location;
}
