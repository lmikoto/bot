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

    private String qq = "710801583";

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

    @Test
    public void img(){
        QQApi.sendImg(qq,"http://img4.tuwandata.com/v3/thumb/jpg/ODYzZiwwLDAsOSwzLDEsLTEsTk9ORSwsLDkw/u/GLDM9lMIBglnFv7YKftLBG4MiepEyVRVs9IerVEKInwLKnryF4jhGDH1akN631uTR66Gmp0h5uE8DxePcqb9hGjeh2azhGK4HiTKUJO3JOQI.jpg");
    }

    @Test
    public void sendGroup(){
        QQApi.sendImgGroup(qq,"http://img4.tuwandata.com/v3/thumb/jpg/ODYzZiwwLDAsOSwzLDEsLTEsTk9ORSwsLDkw/u/GLDM9lMIBglnFv7YKftLBG4MiepEyVRVs9IerVEKInwLKnryF4jhGDH1akN631uTR66Gmp0h5uE8DxePcqb9hGjeh2azhGK4HiTKUJO3JOQI.jpg");
    }

    @Test
    public void like(){
        QQApi.sendLike(qq);
    }
}
