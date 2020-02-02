package io.github.lmikoto.bot.message;

import io.github.lmikoto.bot.api.qq.QQApi;
import io.github.lmikoto.bot.api.qq.QQResponse;
import io.github.lmikoto.bot.api.qq.UserInfo;
import io.github.lmikoto.bot.common.exception.ServiceException;

public class QQUtils {

    public static Boolean isAtMe(String msg){
        String at = String.format("[CQ:at,qq=%s]",getMeQQ());
        return msg.contains(at);
    }

    public static String getMeQQ(){
        QQResponse<UserInfo> response =  QQApi.getLoginInfo();
        if(response.isSuccess()){
            UserInfo userInfo = response.getData();
            return userInfo.getUser_id();
        }
        throw new ServiceException("find.self.error");
    }

    public static String removeAtMe(String msg){
        return msg.replace(String.format("[CQ:at,qq=%s]",getMeQQ()),"");
    }

}
