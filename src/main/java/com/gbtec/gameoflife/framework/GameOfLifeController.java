package com.gbtec.gameoflife.framework;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.gbtec.gameoflife.implementation.GameOfLifeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class GameOfLifeController {

    private final GameOfLifeService gameOfLifeService;

    /**
     * <p> Triggers the initialization or resetting of game of life and delivers the data to the view via websockets
     */
    @PostMapping(path = "/action/init")
    public ResponseEntity<Void> init() {
        gameOfLifeService.init();
        return ResponseEntity.ok().build();
    }

    /**
     * <p> Computes the next generation and delivers the updated data to the view via websockets
     */
    @PostMapping(path = "/action/next")
    public ResponseEntity<Void> next() {
        gameOfLifeService.next();
        return ResponseEntity.ok().build();
    }

    /**
     * <p> Stops automatic computation if "play" was triggered
     */
    @PostMapping(path = "/action/stop")
    public ResponseEntity<Void> stop() {
        gameOfLifeService.stop();
        return ResponseEntity.ok().build();
    }

    /**
     * <p> Computes one generation after the other considering given "playData"
     *
     * Every new generation is delivered to the view via websockets
     */
    @PostMapping(path = "/action/play")
    public ResponseEntity<Void> play(@RequestBody @Valid PlayData playData) {
        gameOfLifeService.play(playData.delayMs());
        return ResponseEntity.ok().build();
    }

    public record PlayData(@Min(0) int delayMs) {}
}
