package io.github.lmikoto.bot.entity;

import io.github.lmikoto.jpa.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "count")
@Data
public class Counter extends BaseEntity {

    private String userId;

    private BigDecimal num;

    private String mark;

    private String type;
}
