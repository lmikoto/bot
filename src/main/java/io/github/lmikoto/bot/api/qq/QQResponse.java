package io.github.lmikoto.bot.api.qq;

import lombok.Data;

import java.io.Serializable;

@Data
public class QQResponse<T> implements Serializable {
    private static final long serialVersionUID = 4085409782533856141L;

    private T data;

    private Long  retcode;

    private String status;

    public Boolean isSuccess(){
        return "ok".equals(status);
    }
}
