package io.github.lmikoto.bot;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.api.xz.XZApi;
import io.github.lmikoto.bot.api.xz.response.XZCurrentWeatherResponse;
import io.github.lmikoto.bot.api.xz.response.XZDailyResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class XZApiTest {

    @Test
    public void daily(){
        XZDailyResponse response = XZApi.getDailyWeather("东营");
        log.info("{}", JacksonUtils.toJson(response));
    }

    @Test
    public void current(){
        XZCurrentWeatherResponse response = XZApi.getCurrentWeather("东营");
        log.info("{}", JacksonUtils.toJson(response));
    }
}
