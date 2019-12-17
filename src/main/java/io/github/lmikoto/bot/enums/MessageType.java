package io.github.lmikoto.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum  MessageType {

    GROUP("group"),
    PRIVATE("private");

    @Getter
    private String value;
}
