package io.github.lmikoto.bot.message;

import ai.olami.ids.*;
import ai.olami.nli.DescObject;
import ai.olami.nli.NLIResult;
import io.github.lmikoto.bot.api.olami.OlamiApi;
import io.github.lmikoto.bot.interfaces.MessageDeal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageService  {

    @Autowired
    private List<MessageDeal> messageDeals;

    public void dealMessage(){
        for(MessageDeal messageDeal: messageDeals){
            if(messageDeal.isMatch()){
                try {
                    messageDeal.dealMsg();
                }catch (Exception e){
                    MessageUtils.reply("不支持的格式");
                }

                if(messageDeal.isBlock()){
                    return;
                }
            }
        }
        dealWithNpl();
    }

//    elif type == '垃圾分类':
//            return nli_obj['semantic'][0]['slots'][0]['value']
//            else:
//            return desc['result']

    private void dealWithNpl(){
        String ans = getNplAns(MessageUtils.getMessageText());
        MessageUtils.reply(ans);
    }

    private String getNplAns(String text){
        NLIResult result = OlamiApi.getNLIResults(text)[0];
        log.info("{}",result);
        String type = result.getType();
        DescObject descObject = result.getDescObject();
        ArrayList arrayList = result.getDataObjects();
        switch (type){
            case "baike":
                ArrayList<BaikeData> baikeData = (ArrayList<BaikeData>)arrayList;
                return baikeData.get(0).getDescription();
            case "joke":
                ArrayList<JokeData> jokeData = (ArrayList<JokeData>)arrayList;
                return jokeData.get(0).getContent();
            case "news":
                ArrayList<NewsData> newsData = (ArrayList<NewsData>)arrayList;
                return newsData.get(0).getDetail();
            case "cooking":
                ArrayList<CookingData> cookingData = (ArrayList<CookingData>)arrayList;
                return cookingData.get(0).getContent();
            case "selection":
                return handleSelectionType(descObject,arrayList);
            case "ds":
                return descObject.getReplyAnswer();

        }
        return "对不起你说的我还听不懂";
    }

    private String handleSelectionType(DescObject descObject,ArrayList arrayList){
        StringBuilder ans = new StringBuilder();
        int index = 1;
        switch (descObject.getType()){
            case "news":
                ans.append(descObject.getReplyAnswer())
                        .append("\n");
                ArrayList<NewsData> newsData = (ArrayList<NewsData>)arrayList;
                for(NewsData data: newsData){
                    ans.append(index++)
                            .append(".")
                            .append(data.getTitle())
                            .append("\n");
                }
                return ans.toString();
            case "poem":
                ans.append(descObject.getReplyAnswer())
                        .append("\n");
                ArrayList<PoemData> poemData = (ArrayList<PoemData>)arrayList;
                for(PoemData data: poemData){
                    ans.append(index++)
                            .append(".")
                            .append(data.getPoemName())
                            .append(", 作者： ")
                            .append(data.getAuthor())
                            .append("\n");
                }
                return ans.toString();
            case "cooking":
                ans.append(descObject.getReplyAnswer())
                        .append("\n");
                ArrayList<CookingData> cookingData = (ArrayList<CookingData>)arrayList;
                for(CookingData data: cookingData){
                    ans.append(index++)
                            .append(".")
                            .append(data.getName())
                            .append("\n");
                }
                return ans.toString();
        }
        return "对不起你说的我还听不懂";
    }
}
