package io.github.lmikoto.bot.service;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.common.utils.CronDateUtils;
import io.github.lmikoto.bot.context.QQMessageContext;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.notice.ChanelDto;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.schedule.model.Schedule;
import io.github.lmikoto.bot.schedule.service.SchedulingService;
import io.github.lmikoto.bot.schedule.tasks.remind.RemindTaskParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
@Slf4j
public class Remind implements MessageDeal {

    String msg[];

    @Autowired
    private SchedulingService schedulingService;

    @Override
    public Boolean isMatch() {
        msg = QQMessageContext.get().getRaw_message().split("提醒我");
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
        ChanelDto chanelDto = new ChanelDto();
        chanelDto.setId(QQMessageContext.get().getUser_id());
        chanelDto.setChanelEnum(ChanelEnum.QQ_PRIVATE);
        remindTaskParam.setNoticeChannel(Collections.singletonList(chanelDto));
        schedule.setParam(JacksonUtils.toJson(remindTaskParam));
        schedule.setCron(getCron(msg[0]));
        schedulingService.addTask(schedule);
    }

    private Long getNextTimestamp(String time){
        if(time.contains("分钟")){
         String times[] = time.split("分钟");
         return System.currentTimeMillis() + Long.valueOf(times[0]) * 60 * 1000;
        }
        return System.currentTimeMillis() - 10000;
    }

    private String getCron(String time){
      return CronDateUtils.getCron(new Date(getNextTimestamp(time)));
    }

}
