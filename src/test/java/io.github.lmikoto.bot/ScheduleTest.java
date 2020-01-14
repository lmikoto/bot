package io.github.lmikoto.bot;

import io.github.lmikoto.bot.schedule.service.SchedulingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;


@SpringBootTest
class ScheduleTest {

    @Autowired
    SchedulingService schedulingService;

    @Test
    void scheduleTest() throws InterruptedException {
        schedulingService.addTask(2L,new TriggerTask(()->{
            System.out.println(111);
        },new CronTrigger("* 0 8 * * ? ")));
        while (true){
            Thread.sleep(4000L);
        }
    }


}
