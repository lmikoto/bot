package io.github.lmikoto.bot.job;

import io.github.lmikoto.bot.api.qq.QQApi;
import io.github.lmikoto.bot.entity.User;
import io.github.lmikoto.bot.entity.UserRepository;
import io.github.lmikoto.bot.enums.Plant;
import io.github.lmikoto.bot.so.UserSO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class QQLikeJob {

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 1 * * ? ")
    public void like(){
        UserSO userSO = new UserSO();
        userSO.setPlant(Plant.QQ.name());
        List<User> users =  userRepository.findAll(userSO);
        users.forEach(user -> {
            QQApi.sendLike(user.getUserId());
        });
    }
}
