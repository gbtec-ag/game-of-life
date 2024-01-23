package com.gbtec.snake.implementation;

import com.gbtec.snake.framework.SnakeCommandProxy;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SnakeService extends SnakeCommandProxy {

    public SnakeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void onConnect() {

    }
}
