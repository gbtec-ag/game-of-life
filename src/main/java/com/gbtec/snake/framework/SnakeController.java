package com.gbtec.snake.framework;

import javax.validation.constraints.Min;

import com.gbtec.snake.implementation.SnakeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SnakeController {

    private final SnakeService gameOfLifeService;

    /**
     * <p>Triggers the initialization or resetting of snake and delivers the data to the view via websockets</p>
     */
    @PostMapping(path = "/action/init")
    public ResponseEntity<Void> init() {
        gameOfLifeService.init();
        return ResponseEntity.ok().build();
    }

    /**
     * <p>Stops automatic computation if "play" was triggered</p>
     */
    @PostMapping(path = "/action/stop")
    public ResponseEntity<Void> stop() {
        gameOfLifeService.stop();
        return ResponseEntity.ok().build();
    }

    /**
     * Starts the game
     */
    @PostMapping(path = "/action/play")
    public ResponseEntity<Void> play() {
        gameOfLifeService.play();
        return ResponseEntity.ok().build();
    }

    public record PlayData(@Min(0) int delayMs) {
    }

    /**
     * <p>Makes it possible to execute code after the UI is connected to the backend via websockets</p>
     */
    @PostMapping(path = "action/onConnect")
    public ResponseEntity<Void> onConnect() {
        gameOfLifeService.onConnect();
        return ResponseEntity.ok().build();
    }

}
