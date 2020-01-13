package io.github.lmikoto.bot.controller;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.notice.ChanelDto;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.schedule.model.Schedule;
import io.github.lmikoto.bot.schedule.repository.ScheduleRepository;
import io.github.lmikoto.bot.schedule.service.SchedulingService;
import io.github.lmikoto.bot.weather.WeatherTaskParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class TestController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private SchedulingService schedulingService;

//    @PostConstruct
    public void test(){
        Schedule schedule = new Schedule();
        schedule.setCron("0/5 * * * * ? ");
        schedule.setIsRepeat(Boolean.FALSE);
        schedule.setTaskBeanName("weatherTask");
        WeatherTaskParam weatherTaskParam = new WeatherTaskParam();
        weatherTaskParam.setLocation("杭州");
        ChanelDto chanelDto = new ChanelDto();
        chanelDto.setChanelEnum(ChanelEnum.QQ_PRIVATE);
        chanelDto.setId("710801583");
        weatherTaskParam.setNoticeChannel(Collections.singletonList(chanelDto));
        schedule.setParam(JacksonUtils.toJson(weatherTaskParam));
        scheduleRepository.save(schedule);

        scheduleRepository.delete(schedule);

//        schedulingService.refreshTasks();
    }
}