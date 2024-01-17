package com.gbtec.gameoflife.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gbtec.gameoflife.implementation.Display;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * Provides methods which allows to interact with the UI
 */
@RequiredArgsConstructor
@Slf4j
public abstract class GameOfLifeCommandProxy {

    private static final String WEBSOCKET_GENERATION_DATA_TOPIC_PATH_MAIN = "/generation/main";
    private static final String WEBSOCKET_GENERATION_DATA_TOPIC_PATH_PREVIEW = "/generation/preview";

    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Method will be executed if you click the "Init" button on the UI
     * Here you should initialize the first generation, prepare the application or even reset / prepare the environment
     * for a new run.
     */
    public void init() {
        log.info("init() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Next" button on the UI
     * Every execution of this method should trigger the computation of the next generation.
     */
    public void next() {
        log.info("next() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Play" button on the UI
     * This method should compute and draw generations one by one considering the given delay between generations
     * @param delayMs Delay between generations in milliseconds
     */
    public void play(int delayMs) {
        log.info("play() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Stop" button on the UI
     * This method should stop if currently running
     */
    public void stop() {
        log.info("stop() is not implemented yet!");
    }

    /**
     * Method will be executed if you click on any cell on the UI
     * @param x X coordinate of the clicked cell
     * @param y Y coordinate of the clicked cell
     */
    public void clickCell(int x, int y) {
        log.info(String.format("clickCell(%s,%s) is not implemented yet!", x, y));
    }

    /**
     * Method will be executed if the UI is loaded
     */
    public void onLoad() {
        log.info("onLoad() is not implemented yet!");
    }

    /**
     * Method will be executed if the UI starts to connect to the server
     */
    public void onConnect() {
        log.info("onConnect() is not implemented yet!");
    }

    /**
     * Method will be executed if the Load button in the Start Conditions tab is clicked
     * This method should load the generation data from the preview display into the main display
     */
    public void onStartConditionsSwap() {
        log.info("onStartConditionsSwap() is not implemented yet!");
    }

    /**
     * Method will be executed if the Clear button in the Start Conditions tab is clicked
     * This method should clear the generation data in the preview display
     */
    public void onStartConditionsClear() {
        log.info("onStartConditionsClear() is not implemented yet!");
    }

    /**
     * Method will be executed if the Preview button in the Start Conditions tab is clicked
     * This method should load the generation data stored at the given storage id into the preview display
     * @param storageId Storage id of the generation data which should be loaded into the preview display
     */
    public void onStartConditionsPreview(int storageId) {
        log.info("onStartConditionsPreview() is not implemented yet!");
    }

    /**
     * Method will be executed if the Save button in the Start Conditions tab is clicked
     * This method should save the generation data from the main display into the storage
     * @param storageId Storage id where the generation data should be saved
     */
    public void onStartConditionsSave(int storageId) {
        log.info("onStartConditionsSave() is not implemented yet!");
    }

    /**
     * Method will be executed if the Start Conditions Tab is loaded
     * This method should initialize the preview display
     */
    public void onInitPreviewDisplay() {
        log.info("onInitPreviewDisplay() is not implemented yet!");
    }


    /**
     * Sends the generation data to the view via websockets. Pre-condition is that the client is connected and registered
     * to the topic.
     * @param generationData Generation data which should be sent to the view
     * @param displayType Type of the display which should be updated
     */
    @SneakyThrows
    protected void drawGeneration(boolean[][] generationData, @NotNull Display.Type displayType) {
        GenerationResponse generationResponse = new GenerationResponse();
        generationResponse.setGenerationData(generationData);

        ObjectMapper objectMapper = new ObjectMapper();
        String val = objectMapper.writeValueAsString(generationResponse);

        simpMessagingTemplate.convertAndSend(displayType.equals(Display.Type.MAIN) ? WEBSOCKET_GENERATION_DATA_TOPIC_PATH_MAIN : WEBSOCKET_GENERATION_DATA_TOPIC_PATH_PREVIEW, val);
    }

    /**
     * Sends the generation data to the view via websockets. Pre-condition is that the client is connected and registered
     * to the topic.
     * @param generationData Generation data which should be sent to the view
     * @param displayType Type of the display which should be updated
     */
    protected void drawGeneration(int[][] generationData, @NotNull Display.Type displayType) {
        boolean[][] tempData = new boolean[generationData.length][];
        for (int y = 0; y < generationData.length; y++) {
            tempData[y] = new boolean[generationData[y].length];
            for (int x = 0; x < generationData[y].length; x++) {
                tempData[y][x] = generationData[y][x] == 1;
            }
        }

        drawGeneration(tempData, displayType);
    }

    @Data
    private static class GenerationResponse {
        private boolean[][] generationData;
    }
}
