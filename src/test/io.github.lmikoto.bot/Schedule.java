package io.github.lmikoto.bot;

import io.github.lmikoto.bot.schedule.service.SchedulingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Schedule {

    @Autowired
    private SchedulingService schedulingConfigurer;

    @Test
    public void test(){
        schedulingConfigurer.addTask(11L,new TriggerTask(()->{
            System.out.println("2222");
        },new CronTrigger("0/5 * * * * ? ")));
    }
}
