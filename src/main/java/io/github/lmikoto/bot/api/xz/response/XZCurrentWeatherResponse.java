package io.github.lmikoto.bot.api.xz.response;

import io.github.lmikoto.bot.api.xz.model.Location;
import io.github.lmikoto.bot.api.xz.model.Now;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class XZCurrentWeatherResponse implements Serializable {
    private static final long serialVersionUID = -8214672482175464587L;

    private List<Result> results;

    @Data
    public static class Result{
        private Location location;
        private Now now;
        private String last_update;
    }

}
