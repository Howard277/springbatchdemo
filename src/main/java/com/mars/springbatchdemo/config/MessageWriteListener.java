package com.mars.springbatchdemo.config;

import com.mars.springbatchdemo.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.batch.api.chunk.listener.ItemWriteListener;
import java.io.Writer;
import java.util.List;

@Slf4j
@Component
public class MessageWriteListener implements ItemWriteListener {
    @Override
    public void beforeWrite(List items) {
    }

    @Override
    public void afterWrite(List items) {
    }

    @Override
    public void onWriteError(List<Object> list, Exception e) throws Exception {
        for (Object obj : list) {
            Message msg = (Message) obj;
        }
    }
}