package io.github.lmikoto.bot;

import io.github.lmikoto.JacksonUtils;
import io.github.lmikoto.bot.api.tools.RollToolsApi;
import io.github.lmikoto.bot.api.tools.Rubbish;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RollToolsTest {

    @Test
    public void rubbish(){
        Rubbish rubbish = RollToolsApi.getRubbishType("女朋友");
        log.info("{}", JacksonUtils.toJson(rubbish));
    }
}
