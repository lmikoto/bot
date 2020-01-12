package io.github.lmikoto.bot.schedule.dto;

import com.google.common.collect.Lists;
import io.github.lmikoto.bot.notice.ChanelDto;
import lombok.Data;

import java.util.List;

@Data
public class BaseTaskParam {

    /**
     * 通知渠道
     */
    protected List<ChanelDto> noticeChannel = Lists.newArrayList();

}
