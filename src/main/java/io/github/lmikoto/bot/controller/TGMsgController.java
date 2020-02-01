package io.github.lmikoto.bot.controller;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Update;
import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.message.MessageService;
import io.github.lmikoto.bot.message.MessageUtils;
import io.github.lmikoto.bot.message.dto.BaseMessage;
import io.github.lmikoto.bot.notice.ChanelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hook")
@Slf4j
public class TGMsgController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public void tgMsg(@RequestBody Object request){
        log.info("tg--{}", JacksonUtils.toJson(request));
        Update update = BotUtils.parseUpdate(JacksonUtils.toJson(request));
        String text = update.message().text();
        BaseMessage baseMessage = new BaseMessage();
        baseMessage.setChanelEnum(ChanelEnum.TG_BOT);
        baseMessage.setMessageText(text);
        MessageUtils.put(baseMessage);
        messageService.dealMessage();
    }
}
