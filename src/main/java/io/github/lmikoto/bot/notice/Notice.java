package io.github.lmikoto.bot.notice;

public interface Notice {

    ChanelEnum noticeChanel();

    void notice(String chanelId,String msg);
}
