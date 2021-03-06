package io.github.lmikoto.bot.message.deal.remind;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.common.utils.CronDateUtils;
import io.github.lmikoto.bot.common.utils.TimeUtils;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.message.MessageUtils;
import io.github.lmikoto.bot.notice.Chanel;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.entity.Schedule;
import io.github.lmikoto.bot.schedule.service.SchedulingService;
import io.github.lmikoto.bot.schedule.tasks.remind.RemindTaskParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

// TODO 支持tg
@Service
@Slf4j
public class Remind implements MessageDeal {

    String msg[];

    @Autowired
    private SchedulingService schedulingService;

    @Override
    public Boolean isMatch() {
        msg = MessageUtils.getMessageText().split("提醒我");
        if(msg.length == 2){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void dealMsg() {
        Schedule schedule = new Schedule();
        schedule.setTaskBeanName("remindTask");
        RemindTaskParam remindTaskParam = new RemindTaskParam();
        remindTaskParam.setRemindContent(msg[1]);
        Chanel chanel = new Chanel();
        chanel.setId(MessageUtils.getQQId());
        chanel.setChanelEnum(ChanelEnum.QQ_PRIVATE);
        remindTaskParam.setNoticeChannel(Collections.singletonList(chanel));
        schedule.setParam(JacksonUtils.toJson(remindTaskParam));
        String cron = CronDateUtils.getCron(msg[0]);
        if(msg[0].startsWith("每天")){
            schedule.setIsRepeat(Boolean.TRUE);
        }
        schedule.setCron(cron);
        Long taskId = schedulingService.addTask(schedule);
        MessageUtils.reply("定时任务保存成功。任务id: " + taskId  +" 下次提醒的时间是 " + TimeUtils.format(CronDateUtils.getNextExec(cron)));
    }

}
