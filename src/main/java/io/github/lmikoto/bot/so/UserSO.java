package io.github.lmikoto.bot.so;

import io.github.lmikoto.jpa.support.DataQueryObject;
import io.github.lmikoto.jpa.support.QueryField;
import io.github.lmikoto.jpa.support.QueryType;
import lombok.Data;

@Data
public class UserSO extends DataQueryObject {

    @QueryField(type = QueryType.EQUAL)
    private String plant;
}
