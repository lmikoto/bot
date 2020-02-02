package io.github.lmikoto.bot.entity;

import io.github.lmikoto.jpa.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
@Data
@SQLDelete(sql = "update schedule set deleted = 1 where id = ? and version = ?")
@Where(clause = "deleted = 0")
public class Schedule extends BaseEntity {

    private String taskBeanName;

    private String param;

    private String cron;

    private Boolean isRepeat = Boolean.FALSE;
}
