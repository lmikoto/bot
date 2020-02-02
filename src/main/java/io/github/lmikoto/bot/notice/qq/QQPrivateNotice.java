package io.github.lmikoto.bot.notice.qq;

import io.github.lmikoto.bot.api.qq.QQApi;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.notice.Notice;
import org.springframework.stereotype.Component;

/**
 * qq 私聊通知
 */
@Component
public class QQPrivateNotice implements Notice {

    @Override
    public ChanelEnum noticeChanel() {
        return ChanelEnum.QQ_PRIVATE;
    }

    @Override
    public void notice(String id,String msg) {
        QQApi.sendPrivate(id,msg);
    }
}
