package io.github.lmikoto.bot.controller;

import io.github.lmikoto.bot.schedule.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/refresh")
    public void refresh(){
        schedulingService.refreshTasks();
    }
}
