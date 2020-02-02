package io.github.lmikoto.bot.message.dto;

import lombok.Data;

@Data
public class QQMessage {

    private String post_type;

    private String message_type;

    private String sub_type;

    private String message_id;

    private String group_id;

    private String user_id;

//    private String message;

    private String raw_message;

    private String font;

    private SenderDto sender;
}
