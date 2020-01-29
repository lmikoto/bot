package io.github.lmikoto.bot.message.deal.remind;

import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.interfaces.MessageSend;
import io.github.lmikoto.bot.message.context.QQMessageContext;
import io.github.lmikoto.bot.schedule.service.SchedulingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StopRemind implements MessageDeal {

    Long id;

    @Autowired
    private SchedulingService schedulingService;

    @Autowired
    private MessageSend messageSend;

    @Override
    public Boolean isMatch() {
        String msg = QQMessageContext.get().getRaw_message();
        if(msg.contains("取消定时任务")){
            String[] splits =  msg.split("取消定时任务");
            id = Long.valueOf(splits[1]);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public void dealMsg() {
        schedulingService.removeTask(id);
        messageSend.reply("取消定时任务成功");
    }

}
