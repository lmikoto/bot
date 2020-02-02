package io.github.lmikoto.bot.api.qq;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 3537833876212473791L;

    private String user_id;

    private String nickname;
}
