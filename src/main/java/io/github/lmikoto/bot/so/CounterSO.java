package io.github.lmikoto.bot.so;

import io.github.lmikoto.jpa.support.DataQueryObject;
import io.github.lmikoto.jpa.support.QueryField;
import io.github.lmikoto.jpa.support.QueryType;
import lombok.Data;

@Data
public class CounterSO extends DataQueryObject {

    @QueryField(type = QueryType.EQUAL)
    private String type;

    @QueryField(type = QueryType.EQUAL)
    private String userId;
}
