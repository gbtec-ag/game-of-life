package com.gbtec.gameoflife.framework;

import com.gbtec.gameoflife.implementation.GameOfLifeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
public class GameOfLifeController {

    private final GameOfLifeService gameOfLifeService;

    /**
     * <p>Triggers the initialization or resetting of game of life and delivers the data to the view via websockets</p>
     */
    @PostMapping(path = "/action/init")
    public ResponseEntity<Void> init() {
        gameOfLifeService.init();
        return ResponseEntity.ok().build();
    }

    /**
     * <p>Computes the next generation and delivers the updated data to the view via websockets</p>
     */
    @PostMapping(path = "/action/next")
    public ResponseEntity<Void> next() {
        gameOfLifeService.next();
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
     * <p>Computes one generation after the other considering given "playData"</p>
     * Every new generation is delivered to the view via websockets
     */
    @PostMapping(path = "/action/play")
    public ResponseEntity<Void> play(@RequestBody @Valid PlayData playData) {
        gameOfLifeService.play(playData.delayMs());
        return ResponseEntity.ok().build();
    }

    public record PlayData(@Min(0) int delayMs) {
    }

    /**
     * <p>Makes the creation of every possible generation data by the user possible</p>
     */
    @PostMapping(path = "/action/clickCell")
    public ResponseEntity<Void> clickCell(@RequestBody @Valid ClickCellData clickCellData) {
        gameOfLifeService.clickCell(clickCellData.x(), clickCellData.y());
        return ResponseEntity.ok().build();
    }

    public record ClickCellData(@Min(0) int x, @Min(0) int y) {
    }

    /**
     * <p>Makes it possible to execute code after the UI is loaded</p>
     */
    @PostMapping(path = "/action/onLoad")
    public ResponseEntity<Void> onLoad() {
        gameOfLifeService.onLoad();
        return ResponseEntity.ok().build();
    }

    /**
     * <p>Makes it possible to execute code after the UI is connected to the backend via websockets</p>
     */
    @PostMapping(path = "action/onConnect")
    public ResponseEntity<Void> onConnect() {
        gameOfLifeService.onConnect();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "action/startConditionsSwap")
    public ResponseEntity<Void> onStartConditionsSwap() {
        gameOfLifeService.onStartConditionsSwap();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "action/startConditionsClear")
    public ResponseEntity<Void> onStartConditionsClear() {
        gameOfLifeService.onStartConditionsClear();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "action/startConditionsPreview")
    public ResponseEntity<Void> onStartConditionsPreview(@RequestBody @Valid StorageData storageData) {
        gameOfLifeService.onStartConditionsPreview(storageData.storageId());
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "action/startConditionsSave")
    public ResponseEntity<Void> onStartConditionsSave(@RequestBody @Valid StorageData storageData) {
        gameOfLifeService.onStartConditionsSave(storageData.storageId());
        return ResponseEntity.ok().build();
    }

    public record StorageData(@Min(0) int storageId) {
    }

    @PostMapping(path = "action/initPreviewDisplay")
    public ResponseEntity<Void> onInitPreviewDisplay() {
        gameOfLifeService.onInitPreviewDisplay();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "action/initStatistics")
    public ResponseEntity<Void> onInitStatistics() {
        gameOfLifeService.onInitStatistics();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/action/saveRules")
    public ResponseEntity<Void> onSaveRules(@RequestBody @Valid RulesData rulesData) {
        gameOfLifeService.onSaveRules(rulesData.gameRules(), rulesData.matrixSize(), rulesData.infiniteDisplay());
        return ResponseEntity.ok().build();
    }

    public record RulesData(GameRuleData[] gameRules, int matrixSize, boolean infiniteDisplay) {
    }

    public record GameRuleData(int ruleId, boolean enabled, boolean nowAlive, @NotNull String operator, int number, boolean alive) {
    }

    @PostMapping(path = "/action/loadRules")
    public ResponseEntity<Void> onLoadRules() {
        gameOfLifeService.onLoadRules();
        return ResponseEntity.ok().build();
    }

}
