package io.github.lmikoto.bot.api.olami;

import ai.olami.cloudService.APIConfiguration;
import ai.olami.cloudService.APIResponse;
import ai.olami.cloudService.TextRecognizer;
import ai.olami.nli.NLIResult;
import io.github.lmikoto.bot.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OlamiApi {

    public static String myAppKey;
    public static String myAppSecret;

    @Autowired
    public void init(@Value("${olami.key}")String myAppKey,@Value("${olami.secret}")String myAppSecret){
        this.myAppKey = myAppKey;
        this.myAppSecret = myAppSecret;
    }

    public static NLIResult[] getNLIResults(String text) {
        APIConfiguration config = new APIConfiguration(myAppKey, myAppSecret, APIConfiguration.LOCALIZE_OPTION_SIMPLIFIED_CHINESE);

        TextRecognizer recoginzer = new TextRecognizer(config);
        try {
            APIResponse response = recoginzer.requestNLI(text);
            if (response.ok() && response.hasData()) {
                return response.getData().getNLIResults();
            }
        }catch (Exception e){
            throw new ServiceException(e.getMessage());
        }
        throw new ServiceException("没有处理");
    }
}
