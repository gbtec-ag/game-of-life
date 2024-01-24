package com.gbtec.snake.implementation;

import com.gbtec.snake.framework.SnakeCommandProxy;
import com.gbtec.snake.framework.SnakeOrientation;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class SnakeService extends SnakeCommandProxy {

    private final SnakeRuntime runtime = new SnakeRuntime(simpMessagingTemplate);

    public SnakeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void onConnect() {
        runtime.resetGame();
    }

    @Override
    public void play() {
        runtime.start();
    }

    @Override
    public void stop() {
        runtime.pause();
    }

    @Override
    public void onOrientationChange(@NotNull SnakeOrientation orientation) {
        runtime.changeOrientation(orientation);
    }
}
