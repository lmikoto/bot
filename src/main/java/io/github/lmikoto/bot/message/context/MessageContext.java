package io.github.lmikoto.bot.message.context;

import io.github.lmikoto.bot.message.dto.BaseMessage;

public class MessageContext {
    private static ThreadLocal<BaseMessage> msgDtoThreadLocal = new ThreadLocal<>();

    public static void put(BaseMessage message){
        msgDtoThreadLocal.set(message);
    }

    public static BaseMessage get(){
        return msgDtoThreadLocal.get();
    }
}
