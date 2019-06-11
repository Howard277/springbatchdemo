package com.mars.springbatchdemo.config;

import com.mars.springbatchdemo.bean.Message;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * 消息处理类
 *
 * @author wuketao
 */
@Component
public class MessageProcessor implements ItemProcessor<Message, Message> {
    @Override
    public Message process(Message message) throws Exception {
        message.setContent(message.getObjectId() + "-" + message.getContent());
        return message;
    }
}
