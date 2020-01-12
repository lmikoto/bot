package io.github.lmikoto.bot.common.exception;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -3267277429956470993L;

    public ServiceException(String msg){
        super(msg);
    }
}
