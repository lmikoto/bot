package io.github.lmikoto.bot.message.deal;

import io.github.lmikoto.bot.entity.Counter;
import io.github.lmikoto.bot.entity.CounterRepository;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import io.github.lmikoto.bot.message.MessageUtils;
import io.github.lmikoto.bot.so.CounterSO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CountMessage implements MessageDeal {

    @Autowired
    private CounterRepository counterRepository;

    private String[] msg;

    private BigDecimal count;

    public Boolean isMatch() {

        String msgStr = MessageUtils.getMessageText();
        if(msgStr.contains("数量")){
            msg = MessageUtils.getMessageText().split(" ");
            if(msg.length == 3){
                try {
                    count = new BigDecimal(msg[1]);
                    return Boolean.TRUE;
                }catch (Exception e){
                    return Boolean.FALSE;
                }
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
        counter.setUserId(MessageUtils.getQQId());
        counter.setType(type);
        counterRepository.save(counter);
        CounterSO counterSO = new CounterSO();
        counterSO.setType(type);
        counterSO.setUserId(MessageUtils.getQQId());

        List<Counter> counters = counterRepository.findAll(counterSO);
        BigDecimal result = BigDecimal.ZERO;
        for(Counter c: counters){
            result = result.add(c.getNum());
        }

        MessageUtils.reply("保存成功 " + "类型: " + type + " 数量: " + count + " 备注: " + mark + " 目前总数: " + result.toPlainString());
    }
}
