package io.github.lmikoto.bot.notice.qq;

import com.google.common.collect.ImmutableMap;
import io.github.lmikoto.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QQMessageSend {

    private static String SEND_MSG;

    @Autowired
    public void init(@Value("${kq.msg}") String SEND_MSG){
        this.SEND_MSG = SEND_MSG;
    }

    public static void sendPrivate(String userId, String msg) {
        HttpUtils.get(SEND_MSG, ImmutableMap.of("user_id",userId,"message",msg));
    }

    public void sendGroup(String group,String msg){
        HttpUtils.get(SEND_MSG, ImmutableMap.of("group_id",group,"message",msg));
    }

}
