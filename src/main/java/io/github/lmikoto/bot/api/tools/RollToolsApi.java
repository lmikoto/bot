package io.github.lmikoto.bot.api.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.ImmutableMap;
import io.github.lmikoto.HttpUtils;
import io.github.lmikoto.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RollToolsApi {

    private static String HOST = "https://www.mxnzp.com/api/";

    private static String appId;

    private static String appSecret;

    @Autowired
    public void init(@Value("${rollTools.app_id}") String appId,@Value("${rollTools.app_secret}") String appSecret){
        this.appId = appId;
        this.appSecret = appSecret;
    }

    public static Rubbish getRubbishType(String name){
        String response = HttpUtils.get(HOST + "/rubbish/type", ImmutableMap.of("name",name,"app_id",appId,"app_secret",appSecret));
        RollToolsApiResponse<Rubbish> toolsApiResponse = JacksonUtils.fromJson(response, new TypeReference<RollToolsApiResponse<Rubbish>>() {});
        return toolsApiResponse.getData();
    }
}
