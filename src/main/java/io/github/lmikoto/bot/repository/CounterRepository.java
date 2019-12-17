package io.github.lmikoto.bot.repository;

import io.github.lmikoto.bot.entity.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Long> {
}
