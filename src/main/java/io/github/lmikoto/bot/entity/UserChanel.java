package io.github.lmikoto.bot.entity;

import io.github.lmikoto.jpa.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_chanel")
@Data
public class UserChanel extends BaseEntity {

    private String param;
}
