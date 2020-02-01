package io.github.lmikoto.bot.entity;

import io.github.lmikoto.jpa.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "config")
public class Config extends BaseEntity {
}
