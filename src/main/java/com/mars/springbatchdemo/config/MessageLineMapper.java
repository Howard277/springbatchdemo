package com.mars.springbatchdemo.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.mars.springbatchdemo.bean.Message;
import org.springframework.batch.item.file.LineMapper;
import java.util.Date;

public class MessageLineMapper implements LineMapper {
    private MappingJsonFactory factory = new MappingJsonFactory();

    @Override
    public Message mapLine(String line, int lineNumber) throws Exception {
        JsonParser parser = factory.createParser(line);
        Message message = new Message();
        message.setContent(line);
        message.setObjectId(line);
        message.setCreatedTime(new Date());
        message.setLastModifiedTime(new Date());
        // 转换逻辑
        return message;
    }
}