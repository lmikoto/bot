package io.github.lmikoto.bot.schedule.tasks.remind;

import io.github.lmikoto.bot.schedule.dto.BaseTaskParam;
import lombok.Data;

@Data
public class RemindTaskParam extends BaseTaskParam {

    String remindContent;
}
