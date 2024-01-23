package com.gbtec.snake.framework;

import com.gbtec.snake.implementation.SnakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
public class SnakeController {

    private final SnakeService gameOfLifeService;

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

    /**
     * <p>Changes the orientation of the snake</p>
     */
    @PostMapping(path = "/action/changeOrientation")
    public ResponseEntity<Void> onOrientationChange(@RequestBody @Valid OrientationData data) {
        gameOfLifeService.onOrientationChange(data.orientation());
        return ResponseEntity.ok().build();
    }

    public record OrientationData(@NotNull SnakeOrientation orientation) {
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
