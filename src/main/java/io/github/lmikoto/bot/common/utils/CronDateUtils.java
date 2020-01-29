package io.github.lmikoto.bot.common.utils;

import io.github.lmikoto.bot.common.exception.ServiceException;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Calendar.getInstance;

public class CronDateUtils {
    private static final String CRON_DATE_FORMAT = "ss mm HH dd MM ?";

    private static final String CRON_DATE_FORMAT_EVERY_DAY = "ss mm HH ? MM ?";

    public static String getCron(final Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        String formatTimeStr = "";
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }


    public static Date getNextExec(String cron){
        CronTrigger trigger = new CronTrigger(cron);
        SimpleTriggerContext triggerContext = new SimpleTriggerContext();
        Date date = new Date();
        triggerContext.update(null, null, date);
        return trigger.nextExecutionTime(triggerContext);
    }

    public static String getCron(String time){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(CRON_DATE_FORMAT);
        Calendar now = Calendar.getInstance();
        if(time.contains("点")){
            Pattern p = null;
            if(time.contains("每天")){
                p= Pattern.compile("每天(.*?)点(.*)");
                sdf = new SimpleDateFormat(CRON_DATE_FORMAT_EVERY_DAY);
                Matcher m = p.matcher(time);
                while (m.find()){
                    Calendar calendar = getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, DigitUtil.parseDigits(m.group(1)));
                    calendar.set(Calendar.MINUTE, DigitUtil.parseDigits(m.group(2)));
                    calendar.set(Calendar.SECOND, 0);
                    date = calendar.getTime();
                }
            }else{
                p= Pattern.compile("(.*?)点(.*)");
                Matcher m = p.matcher(time);
                while (m.find()){
                    Calendar calendar = getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, DigitUtil.parseDigits(m.group(1)));
                    calendar.set(Calendar.MINUTE, DigitUtil.parseDigits(m.group(2)));
                    calendar.set(Calendar.SECOND, 0);
                    if(calendar.compareTo(now) < 0) {
                        calendar.add(Calendar.HOUR,24);
                    }
                    date = calendar.getTime();
                }
            }

        } else {
            date = new Date(getNextTimestamp(time));
        }

        String formatTimeStr = "";
        if (Objects.nonNull(date)) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    public static Long getNextTimestamp(String time){
        TimeEnums timeEnums = TimeEnums.contain(time);
        if(Objects.nonNull(timeEnums)){
            String times[] = time.split(timeEnums.getDesc());
            return System.currentTimeMillis() + DigitUtil.parseDigits(times[0]) * timeEnums.getMillisecond();
        }
        throw new ServiceException("未找到对应的时间");
    }

}
