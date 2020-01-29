package io.github.lmikoto.bot.schedule.dto;

import com.google.common.collect.Lists;
import io.github.lmikoto.bot.notice.Chanel;
import lombok.Data;

import java.util.List;

@Data
public class BaseTaskParam {

    /**
     * 通知渠道
     */
    protected List<Chanel> noticeChannel = Lists.newArrayList();

}
