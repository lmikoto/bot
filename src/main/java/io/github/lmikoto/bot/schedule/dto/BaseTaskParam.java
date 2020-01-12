package io.github.lmikoto.bot.schedule.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public abstract class BaseTaskParam {

    /**
     * 通知渠道
     */
    protected List<String> noticeChannel = Lists.newArrayList();

    /**
     * 通知去到的id
     */
    protected List<List<String>> noticeIds = Lists.newArrayList();
}
