package io.github.lmikoto.bot.message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendSticker;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TGBotUtils {

    private static String token;

    private static String botId;

    private static TelegramBot bot;

    @Autowired
    public void initBot(@Value("${tg.id}") String botId,@Value("${tg.token}") String token){
        this.botId = botId;
        this.token = token;
        this.bot = new TelegramBot(token);
    }

    public static SendResponse sendMessage(String msg){
        return bot.execute(new SendMessage(botId, msg));
    }

    public static SendResponse sendSticker(String url){
        return bot.execute(new SendSticker(botId, url));
    }

}
