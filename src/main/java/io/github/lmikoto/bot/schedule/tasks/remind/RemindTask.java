package io.github.lmikoto.bot.schedule.tasks.remind;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.schedule.service.Task;
import org.springframework.stereotype.Component;

@Component
public class RemindTask implements Task {
    @Override
    public String getNoticeMsg(String param) {
        RemindTaskParam remindTaskParam = JacksonUtils.fromJson(param,RemindTaskParam.class);
        return remindTaskParam.getRemindContent();
    }
}
