package io.github.lmikoto.bot.message;

import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.interfaces.MessageSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService  {

    @Autowired
    private List<MessageDeal> messageDeals;

    @Autowired
    private MessageSend messageSend;

    public void dealMessage(){
        for(MessageDeal messageDeal: messageDeals){
            if(messageDeal.isMatch()){
                try {
                    messageDeal.dealMsg();
                }catch (Exception e){
                    messageSend.reply("不支持的格式");
                }

                if(messageDeal.isBlock()){
                    break;
                }
            }
        }
    }


}
