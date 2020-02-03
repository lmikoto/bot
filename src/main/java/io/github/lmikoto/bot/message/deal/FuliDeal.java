package io.github.lmikoto.bot.message.deal;

import io.github.lmikoto.bot.entity.Fuli;
import io.github.lmikoto.bot.entity.FuliRepository;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.message.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FuliDeal implements MessageDeal {

    private Integer num = 5;

    @Autowired
    private FuliRepository fuliRepository;

    @Override
    public Boolean isMatch() {
        return "福利".equals(MessageUtils.getMessageText()) || " 福利".equals(MessageUtils.getMessageText()) ;
    }

    @Override
    public void dealMsg() {
        Long qty = fuliRepository.count();
        for(int i = 0; i< num; i++){
            int idx = (int)(Math.random() * qty);
            Page<Fuli> questionPage = fuliRepository.findAll(PageRequest.of(idx, 1));
            Fuli fuli = null;
            if (questionPage.hasContent()) {
                fuli = questionPage.getContent().get(0);
                MessageUtils.replyImg(fuli.getUrl());
            }
        }
    }
}
