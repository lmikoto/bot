package io.github.lmikoto.bot.interfaces;

public interface MessageDeal {

    Boolean isMatch();

    void dealMsg();

    default Boolean isBlock(){
        return Boolean.TRUE;
    }
}
