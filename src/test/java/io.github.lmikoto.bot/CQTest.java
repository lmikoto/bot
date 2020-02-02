package io.github.lmikoto.bot;

import io.github.lmikoto.bot.api.qq.QQApi;
import io.github.lmikoto.bot.api.qq.QQResponse;
import io.github.lmikoto.bot.api.qq.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class CQTest {

    @Test
    public void CQ(){
        QQApi.sendPrivate("710801583","[CQ:face,id=184]");
    }

    @Test
    public void getLoginInfo(){
        QQResponse<UserInfo> response = QQApi.getLoginInfo();
        UserInfo userInfo = response.getData();
        log.info(userInfo.getUser_id());
    }
}
