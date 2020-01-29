package io.github.lmikoto.bot.notice.qq;

import io.github.lmikoto.bot.message.context.QQMessageContext;
import io.github.lmikoto.bot.message.dto.QQMessage;
import io.github.lmikoto.bot.enums.MessageType;

import java.util.Objects;

public class QQMessageUtils {

    public static Boolean isGroup(){
        QQMessage QQMessage = QQMessageContext.get();
        return Objects.equals(QQMessage.getMessage_type(), MessageType.GROUP.getValue());
    }

    public static Boolean isPrivate(){
        QQMessage QQMessage = QQMessageContext.get();
        return Objects.equals(QQMessage.getMessage_type(), MessageType.PRIVATE.getValue());
    }
}
