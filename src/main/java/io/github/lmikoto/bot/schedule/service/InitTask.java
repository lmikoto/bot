package io.github.lmikoto.bot.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitTask {

    @Autowired
    private SchedulingService schedulingService;

    /**
     * 启动之后把所有的task注册进去
     */
    @PostConstruct
    public void init(){
        schedulingService.initTask();
    }

}
