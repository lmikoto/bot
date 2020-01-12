package io.github.lmikoto.bot.notice.qq;

import io.github.lmikoto.bot.context.QQMessageContext;
import io.github.lmikoto.bot.dto.MsgDto;
import io.github.lmikoto.bot.enums.MessageType;

import java.util.Objects;

public class QQMessageUtils {

    public static Boolean isGroup(){
        MsgDto msgDto = QQMessageContext.get();
        return Objects.equals(msgDto.getMessage_type(), MessageType.GROUP.getValue());
    }

    public static Boolean isPrivate(){
        MsgDto msgDto = QQMessageContext.get();
        return Objects.equals(msgDto.getMessage_type(), MessageType.PRIVATE.getValue());
    }
}
