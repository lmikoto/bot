package io.github.lmikoto.bot.schedule.repository;

import io.github.lmikoto.bot.schedule.model.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
}
