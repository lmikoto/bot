package io.github.lmikoto.bot.notice;

public interface Notice {

    NoticeChanelEnum noticeChanel();

    void notice(String chanelId);
}
