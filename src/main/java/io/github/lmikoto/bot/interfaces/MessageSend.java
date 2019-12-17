package io.github.lmikoto.bot.interfaces;

public interface MessageSend {

    default void sendPrivate(String userId,String msg){

    }

    default void sendGroup(String group,String msg){

    }

    default void reply(String msg){

    }
}
