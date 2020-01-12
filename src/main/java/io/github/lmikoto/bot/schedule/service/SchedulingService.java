package io.github.lmikoto.bot.schedule.service;

import com.google.common.collect.Maps;
import io.github.lmikoto.bot.common.exception.ServiceException;
import io.github.lmikoto.bot.common.utils.SpringUtil;
import io.github.lmikoto.bot.schedule.model.Schedule;
import io.github.lmikoto.bot.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;

@Service
public class SchedulingService {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private ScheduleRepository scheduleRepository;

    private Map<Long, Future<?>> taskFutures = Maps.newConcurrentMap();


    public void addTask(Long taskId, TriggerTask triggerTask){
        if(taskFutures.containsKey(taskId)){
            throw new ServiceException("task.has.registered");
        }
        Future futureTask = taskScheduler.schedule(triggerTask.getRunnable(),triggerTask.getTrigger());
        taskFutures.put(taskId, futureTask);
    }

    public void addTask(Schedule schedule){
        Task task = (Task) SpringUtil.getBean(schedule.getTaskBeanName());
        if(taskFutures.containsKey(schedule.getId())){
            throw new ServiceException("task.has.registered");
        }
        Runnable runnable = () -> {
            task.excuse(schedule.getParam());
            if(Objects.equals(Boolean.FALSE,schedule.getIsRepeat())){
                scheduleRepository.delete(schedule);
                stopTask(schedule.getId());
            }
        };
        Future futureTask = taskScheduler.schedule(runnable,new CronTrigger(schedule.getCron()));
        taskFutures.put(schedule.getId(), futureTask);
    }


    public void stopTask(Long taskId){
        if(!taskFutures.containsKey(taskId)){
            throw new ServiceException("no.found.task");
        }
        taskFutures.get(taskId).cancel(true);
        taskFutures.remove(taskId);
    }


    /**
     * 启动之后把所有的task注册进去
     */
    @PostConstruct
    public void initTask(){

    }
}
