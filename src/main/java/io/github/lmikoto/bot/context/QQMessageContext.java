package io.github.lmikoto.bot.context;

import io.github.lmikoto.bot.dto.MsgDto;

public class QQMessageContext {

    private static ThreadLocal<MsgDto> msgDtoThreadLocal = new ThreadLocal<>();

    public static void put(MsgDto msgDto){
        msgDtoThreadLocal.set(msgDto);
    }

    public static MsgDto get(){
        return msgDtoThreadLocal.get();
    }
}
