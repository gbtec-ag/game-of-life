package com.gbtec.gameoflife.basic;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.gbtec.gameoflife.custom.GameOfLifeService;

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
     * Init should trigger the initialization or reset of game of life and deliver the data to the view via websockets
     */
    @PostMapping(path = "/action/init")
    public ResponseEntity<Void> init() {
        gameOfLifeService.init();
        return ResponseEntity.ok().build();
    }

    /**
     * Next should compute the next generation and deliver the updated data to the view via websockets
     */
    @PostMapping(path = "/action/next")
    public ResponseEntity<Void> next() {
        gameOfLifeService.next();
        return ResponseEntity.ok().build();
    }

    /**
     * Stop should stop automatic computation, in case "play" was triggered
     */
    @PostMapping(path = "/action/stop")
    public ResponseEntity<Void> stop() {
        gameOfLifeService.stop();
        return ResponseEntity.ok().build();
    }

    /**
     * Play should compute one generation after other considering given "playData"
     * Every new generation should be delivered to the view via websockets
     */
    @PostMapping(path = "/action/play")
    public ResponseEntity<Void> play(@RequestBody @Valid PlayData playData) {
        gameOfLifeService.play(playData.delayMs());
        return ResponseEntity.ok().build();
    }

    public record PlayData(@Min(0) int delayMs) {}
}
