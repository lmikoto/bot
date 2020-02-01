package io.github.lmikoto.bot.controller;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.message.MessageService;
import io.github.lmikoto.bot.message.MessageUtils;
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

@RestController
@RequestMapping("/msg")
@Slf4j
public class QQMsgController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void getMsg(@RequestBody QQMessage msg){
        log.info("qq--{}", JacksonUtils.toJson(msg));
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setMessageText(msg.getRaw_message());
        if(QQMessageUtils.isGroup(msg)){
            baseMessage.setChanelEnum(ChanelEnum.QQ_GROUP);
        }else{
            baseMessage.setChanelEnum(ChanelEnum.QQ_PRIVATE);
        }
        baseMessage.setMessage(JacksonUtils.toJson(msg));
        MessageUtils.put(baseMessage);
        messageService.dealMessage();
    }
}
