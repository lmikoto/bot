package io.github.lmikoto.bot.message.context;

import io.github.lmikoto.bot.message.dto.QQMessage;

public class QQMessageContext {

    private static ThreadLocal<QQMessage> msgDtoThreadLocal = new ThreadLocal<>();

    public static void put(QQMessage QQMessage){
        msgDtoThreadLocal.set(QQMessage);
    }

    public static QQMessage get(){
        return msgDtoThreadLocal.get();
    }
}
