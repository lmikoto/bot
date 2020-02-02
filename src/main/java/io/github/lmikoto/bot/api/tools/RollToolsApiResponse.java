package io.github.lmikoto.bot.api.tools;

import lombok.Data;

@Data
public class RollToolsApiResponse<T> {

    private String code;

    private String msg;

    private T data;

}
