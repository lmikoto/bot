package io.github.lmikoto.bot.notice.qq;

import io.github.lmikoto.bot.enums.MessageType;
import io.github.lmikoto.bot.message.dto.QQMessage;

import java.util.Objects;

public class QQMessageUtils {

    public static Boolean isGroup(QQMessage qqMessage){
        return Objects.equals(qqMessage.getMessage_type(), MessageType.GROUP.getValue());
    }
}
