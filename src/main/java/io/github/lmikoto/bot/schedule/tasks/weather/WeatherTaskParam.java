package io.github.lmikoto.bot.schedule.tasks.weather;

import io.github.lmikoto.bot.schedule.dto.BaseTaskParam;
import lombok.Data;

@Data
public class WeatherTaskParam extends BaseTaskParam {
    private String location;
}
