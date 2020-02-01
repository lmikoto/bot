package io.github.lmikoto.bot.message.dto;

import io.github.lmikoto.bot.notice.ChanelEnum;
import lombok.Data;

@Data
public class BaseMessage {

    private ChanelEnum chanelEnum;

    private String messageText;

    private String message;
}
