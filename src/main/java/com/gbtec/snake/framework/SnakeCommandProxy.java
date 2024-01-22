package com.gbtec.snake.framework;

import com.fasterxml.jackson.databind.ObjectMapper;

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
public abstract class SnakeCommandProxy {

    private static final String WEBSOCKET_DISPLAY_DATA_TOPIC_PATH = "/display";

    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Method will be executed if you click the "Init" button on the UI
     * Here you should initialize the first environment, prepare the application or even reset / prepare the environment
     * for a new run.
     */
    public void init() {
        log.info("init() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Play" button on the UI
     * This method should compute and set the new data to the canvas
     */
    public void play() {
        log.info("play() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Stop" button on the UI
     * This method should stop if currently running
     */
    public void stop() {
        log.info("stop() is not implemented yet!");
    }

    public void onConnect() {
        log.info("onConnect() is not implemented yet!");
    }

    /**
     * Sends the display data to the view via websockets. Pre-condition is that the client is connected and registered
     * to the topic.
     * @param displayData The data to send
     */
    @SneakyThrows
    protected void updateDisplay(@NotNull DisplayCell[][] displayData) {
        DisplayResponse displayResponse = new DisplayResponse();
        displayResponse.setDisplayData(displayData);

        ObjectMapper objectMapper = new ObjectMapper();
        String val = objectMapper.writeValueAsString(displayResponse);

        simpMessagingTemplate.convertAndSend(WEBSOCKET_DISPLAY_DATA_TOPIC_PATH, val);
    }

    @Data
    private static class DisplayResponse {
        private DisplayCell[][] displayData;
    }
}
