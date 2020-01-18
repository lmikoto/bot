package io.github.lmikoto.bot.controller;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.context.QQMessageContext;
import io.github.lmikoto.bot.dto.MsgDto;
import io.github.lmikoto.bot.message.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
@Slf4j
public class MsgController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Object getMsg(@RequestBody MsgDto msg){
        log.info("{}", JacksonUtils.toJson(msg));
        QQMessageContext.put(msg);
        messageService.dealMessage();
        return  "";
    }
}
