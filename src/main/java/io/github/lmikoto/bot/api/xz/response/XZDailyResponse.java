package io.github.lmikoto.bot.api.xz.response;

import io.github.lmikoto.bot.api.xz.model.Daily;
import io.github.lmikoto.bot.api.xz.model.Location;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class XZDailyResponse implements Serializable {
    private static final long serialVersionUID = -7570000850763801484L;

    private List<XZDailyResponse.Result> results;

    @Data
    public static class Result{
        private Location location;
        private List<Daily> daily;
        private String last_update;
    }
}
