package io.github.lmikoto.bot.schedule.service;

import com.google.common.collect.Maps;
import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.common.exception.ServiceException;
import io.github.lmikoto.bot.common.utils.SpringUtil;
import io.github.lmikoto.bot.notice.NoticeService;
import io.github.lmikoto.bot.schedule.dto.BaseTaskParam;
import io.github.lmikoto.bot.entity.Schedule;
import io.github.lmikoto.bot.entity.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Future;

@Service
@Slf4j
public class SchedulingService implements CommandLineRunner {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private NoticeService noticeService;

    private Map<Long, Future<?>> taskFutures = Maps.newConcurrentMap();


    public void addTask(Long taskId, TriggerTask triggerTask){
        if(taskFutures.containsKey(taskId)){
            throw new ServiceException("task.has.registered");
        }
        Future futureTask = taskScheduler.schedule(triggerTask.getRunnable(),triggerTask.getTrigger());
        taskFutures.put(taskId, futureTask);
    }

    @Transactional
    public Long addTask(Schedule schedule){

        if(Objects.isNull(schedule.getId())){
            scheduleRepository.save(schedule);
        }

        Task task = (Task) SpringUtil.getBean(schedule.getTaskBeanName());
        if(taskFutures.containsKey(schedule.getId())){
            throw new ServiceException("task.has.registered");
        }
        Runnable runnable = () -> {
            log.info("exec task {}",JacksonUtils.toJson(schedule));
            String noticeMsg =  task.getNoticeMsg(schedule.getParam());

            if(Objects.nonNull(noticeMsg)){
                BaseTaskParam baseTaskParam = JacksonUtils.fromJson(schedule.getParam(),BaseTaskParam.class);
                noticeService.send(baseTaskParam.getNoticeChannel(),noticeMsg);
            }

            if(Objects.equals(Boolean.FALSE,schedule.getIsRepeat())){
                scheduleRepository.delete(schedule);
                stopTask(schedule.getId());
            }
        };
        Future futureTask = taskScheduler.schedule(runnable,new CronTrigger(schedule.getCron()));
        taskFutures.put(schedule.getId(), futureTask);
        log.info("add task {}",schedule);
        return schedule.getId();
    }

    public void removeTask(Long id){
        scheduleRepository.deleteById(id);
        stopTask(id);
    }


    public void stopTask(Long taskId){
        if(!taskFutures.containsKey(taskId)){
            throw new ServiceException("no.found.task");
        }
        taskFutures.get(taskId).cancel(true);
        taskFutures.remove(taskId);
    }

    public void stopAllTask(){
        taskFutures.forEach((k,v) -> {
            v.cancel(true);
        });
        taskFutures.clear();
    }

    public void refreshTasks(){
        stopAllTask();
        initTask();
    }


    public void initTask(){
        Iterable<Schedule> list = scheduleRepository.findAll();
        list.forEach(this::addTask);
    }

    @Override
    public void run(String... args) throws Exception {
        initTask();
    }
}
