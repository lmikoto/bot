package io.github.lmikoto.bot.notice.qq;

import com.google.common.collect.ImmutableMap;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.bot.context.QQMessageContext;
import io.github.lmikoto.bot.interfaces.MessageSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QQMessageSend implements MessageSend {


    @Value("${kq.msg}")
    private String SEND_MSG;

    // TODO 干掉这个类
    @Override
    public void sendPrivate(String userId, String msg) {
        HttpUtils.get(SEND_MSG, ImmutableMap.of("user_id",userId,"message",msg));
    }

    @Override
    public void sendGroup(String group,String msg){
        HttpUtils.get(SEND_MSG, ImmutableMap.of("group_id",group,"message",msg));
    }

    @Override
    public void reply(String msg){
        if(QQMessageUtils.isGroup()){
            sendGroup(QQMessageContext.get().getGroup_id(),msg);
        }
        if(QQMessageUtils.isPrivate()){
            sendPrivate(QQMessageContext.get().getUser_id(),msg);
        }
    }

}
