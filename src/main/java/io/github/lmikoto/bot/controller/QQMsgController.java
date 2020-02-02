package io.github.lmikoto.bot.controller;

import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.message.MessageService;
import io.github.lmikoto.bot.message.MessageUtils;
import io.github.lmikoto.bot.message.QQUtils;
import io.github.lmikoto.bot.message.dto.BaseMessage;
import io.github.lmikoto.bot.message.dto.QQMessage;
import io.github.lmikoto.bot.notice.ChanelEnum;
import io.github.lmikoto.bot.notice.qq.QQMessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/msg")
@Slf4j
public class QQMsgController {

    @Autowired
    private MessageService messageService;

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @PostMapping
    public void getMsg(@RequestBody QQMessage msg){
        log.info("qq--{}", JacksonUtils.toJson(msg));
        executorService.execute(()->{
            //做群转发用
            HttpUtils.post("https://tg-qq-docker.herokuapp.com/",msg);
        });

        BaseMessage baseMessage = new BaseMessage();
        if(QQMessageUtils.isGroup(msg)){
            // 群消息判断是不是发给自己的
            if(!QQUtils.isAtMe(msg.getRaw_message())){
               return;
            }
            // 擦除@自己 使消息正确处理
            msg.setRaw_message(QQUtils.removeAtMe(msg.getRaw_message()));
            baseMessage.setChanelEnum(ChanelEnum.QQ_GROUP);
        }else{
            baseMessage.setChanelEnum(ChanelEnum.QQ_PRIVATE);
        }
        baseMessage.setMessageText(msg.getRaw_message());
        baseMessage.setMessage(JacksonUtils.toJson(msg));
        MessageUtils.put(baseMessage);
        messageService.dealMessage();

    }
}
