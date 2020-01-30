package io.github.lmikoto.bot.message.deal;

import io.github.lmikoto.bot.message.context.QQMessageContext;
import io.github.lmikoto.bot.entity.Counter;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.interfaces.MessageSend;
import io.github.lmikoto.bot.entity.CounterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
@Slf4j
public class CountMessage implements MessageDeal {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private MessageSend messageSend;

    private String[] msg;

    private BigDecimal count;

    public Boolean isMatch() {
        msg = QQMessageContext.get().getRaw_message().split(" ");
        if(msg.length == 3){
            try {
                count = new BigDecimal(msg[1]);
                return Boolean.TRUE;
            }catch (Exception e){
                return Boolean.FALSE;
            }
        }
        return Boolean.FALSE;
    }

    @Transactional(rollbackOn = Exception.class)
    public void dealMsg() {
        String type = msg[0];
        String mark = msg[2];
        Counter counter = new Counter();
        counter.setNum(count);
        counter.setMark(mark);
        counter.setUserId(QQMessageContext.get().getUser_id());
        counter.setType(type);
        counterRepository.save(counter);
        messageSend.reply(" 保存成功 " + "类型: " + type + " 数量: " + count + " 备注: " + mark);
    }
}
