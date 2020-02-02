package io.github.lmikoto.bot.api.tools;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Rubbish implements Serializable {

    private static final long serialVersionUID = 5598515110594865275L;
    private Aim aim;

    private List<Aim> recommendList;


    @Data
    public static class Aim{
        private String goodsName;

        private String goodsType;
    }
}
