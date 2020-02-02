package io.github.lmikoto.bot.api.qq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QQApi {

    private static String HOST;

    private static Integer length = 400;

    @Autowired
    public void init(@Value("${kq.host}") String HOST){
        this.HOST = HOST;
    }

    public static void sendPrivate(String userId, String msg) {
        if(msg.length() > length){
            String sendMsg = msg.substring(0,length);
            HttpUtils.get(HOST + "/send_msg", ImmutableMap.of("user_id",userId,"message",sendMsg));
            sendPrivate(userId,msg.substring(length));
        }else{
            HttpUtils.post(HOST + "/send_msg", ImmutableMap.of("user_id",userId,"message",msg));
        }

    }

    public static void sendGroup(String group,String msg){
        if(msg.length() > length){
            String sendMsg = msg.substring(0,length);
            HttpUtils.get(HOST + "/send_msg", ImmutableMap.of("group_id",group,"message",sendMsg));
            sendGroup(group,msg.substring(length));
        }else{
            HttpUtils.get(HOST + "/send_msg", ImmutableMap.of("group_id",group,"message",msg));
        }
    }

    public static QQResponse<UserInfo> getLoginInfo(){
       return JacksonUtils.fromJson(HttpUtils.get(HOST + "/get_login_info"), new TypeReference<QQResponse<UserInfo>>() {});
    }

    public static void sendImg(String userId,String url){
        String img = String.format("[CQ:image,file=%s]",url);
        HttpUtils.post(HOST + "/send_msg", ImmutableMap.of("user_id",userId,"message",img));
    }

    public static void sendImgGroup(String group,String url){
        String img = String.format("[CQ:image,file=%s]",url);
        HttpUtils.get(HOST + "/send_msg", ImmutableMap.of("group_id",group,"message",img));
    }

    public static void sendLike(String qq){
        HttpUtils.get(HOST + "/send_like", ImmutableMap.of("user_id",qq,"times","10"));
    }

}
