package com.mars.springbatchdemo.config;

import com.mars.springbatchdemo.bean.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;

import static java.lang.String.format;

@Slf4j
@Component
public class MessageItemReadListener implements ItemReadListener {
    private Writer errorWriter;

    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(Object o) {

    }

    @Override
    public void onReadError(Exception ex) {
        try {
            errorWriter.write(format("%s%n", ex.getMessage()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}