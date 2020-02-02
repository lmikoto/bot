package io.github.lmikoto.bot.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "fuli")
@Data
public class Fuli {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String desc;

    private String url;

    private String code;
}
