package io.github.lmikoto.bot.notice;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeService implements ApplicationContextAware {

    private Map<ChanelEnum,Notice> noticeMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String,Notice> beanMaps =  applicationContext.getBeansOfType(Notice.class);
        noticeMap = Maps.newConcurrentMap();
        beanMaps.forEach((k,v)->{
            noticeMap.put(v.noticeChanel(),v);
        });
    }

    public void send(List<ChanelDto> noticeChanelList, String msg){
        noticeChanelList.forEach(i->{
            send(i,msg);
        });
    }

    public void send(ChanelDto chanelDto,String msg){
        Notice notice = getNotice(chanelDto.getChanelEnum());
        notice.notice(chanelDto.getId(),msg);
    }

    public Notice getNotice(ChanelEnum chanelEnum){
        return noticeMap.get(chanelEnum);
    }
}
