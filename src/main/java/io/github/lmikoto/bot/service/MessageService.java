package io.github.lmikoto.bot.service;

import io.github.lmikoto.bot.interfaces.MessageDeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService  {

    @Autowired
    private List<MessageDeal> messageDeals;

    public void dealMessage(){
        for(MessageDeal messageDeal: messageDeals){
            if(messageDeal.isMatch()){
                messageDeal.dealMsg();
                if(messageDeal.isBlock()){
                    break;
                }
            }
        }
    }


}
