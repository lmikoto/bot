package io.github.lmikoto.bot.notice.qq;

import com.google.common.collect.ImmutableMap;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.notice.Notice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * qq 私聊通知
 */
@Component
public class QQPrivateNotice implements Notice {

    @Value("${kq.msg}")
    private String SEND_MSG;

    @Override
    public ChanelEnum noticeChanel() {
        return ChanelEnum.QQ_PRIVATE;
    }

    @Override
    public void notice(String id,String msg) {
        HttpUtils.get(SEND_MSG, ImmutableMap.of("user_id",id,"message",msg));
    }
}
