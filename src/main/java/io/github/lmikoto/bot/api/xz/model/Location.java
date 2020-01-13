package io.github.lmikoto.bot.api.xz.model;

import lombok.Data;

@Data
public class Location{
    private String id;
    private String name;
    private String country;
    private String path;
    private String timezone;
    private String timezone_offset;
}

