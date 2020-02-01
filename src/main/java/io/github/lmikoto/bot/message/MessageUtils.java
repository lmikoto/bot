package io.github.lmikoto.bot.message;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.message.dto.BaseMessage;
import io.github.lmikoto.bot.message.dto.QQMessage;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.notice.qq.QQMessageSend;

import java.util.Objects;

public class MessageUtils {
    private static ThreadLocal<BaseMessage> msgDtoThreadLocal = new ThreadLocal<>();

    public static void put(BaseMessage message){
        msgDtoThreadLocal.set(message);
    }

    public static BaseMessage get(){
        return msgDtoThreadLocal.get();
    }

    public static ChanelEnum getChanel(){
        return get().getChanelEnum();
    }

    public static String getMessageText(){
        return get().getMessageText();
    }

    public static String getMessage(){
        return get().getMessage();
    }

    public static void reply(String msg){
        if(Objects.nonNull(msg)){
            switch (getChanel()){
                case TG_BOT:
                    TGBotUtils.sendMessage(msg);
                    break;
                case QQ_PRIVATE:
                    QQMessageSend.sendPrivate(getQQId(),msg);
                    break;
                default:
            }
        }
    }

    public static String getQQId(){
        QQMessage qqMessage = JacksonUtils.fromJson(getMessage(),QQMessage.class);
        return qqMessage.getUser_id();
    }
}
