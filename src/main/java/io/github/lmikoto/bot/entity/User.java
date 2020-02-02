package io.github.lmikoto.bot.entity;

import io.github.lmikoto.jpa.entity.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "user")
public class User extends BaseEntity {

    private String userId;

    private String plant;
}
