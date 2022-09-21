package com.gbtec.gameoflife.basic;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Provides methods which allows to interact with the UI
 */
@RequiredArgsConstructor
public abstract class GameOfLifeCommandProxy {

    private static final String PRINTING_GOL_GENERATION_TOPIC_PATH = "/gol/generation";

    private final SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Method will be executed if you click the "Init" button on the UI
     * Here you should initialize the first generation, prepare the application or even reset / prepare the environment
     * for a new run.
     */
    public void init() {
        System.out.println("init() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Next" button on the UI
     * Every execution of this method should trigger the computation of the next generation.
     */
    public void next() {
        System.out.println("next() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Play" button on the UI
     * This method should compute and draw generations one by one considering the given delay between generations
     * @param delayMs Delay between generations in milliseconds
     */
    public void play(int delayMs) {
        System.out.println("play() is not implemented yet!");
    }

    /**
     * Method will be executed if you click the "Stop" button on the UI
     * This method should stop if currently running
     */
    public void stop() {
        System.out.println("stop() is not implemented yet!");
    }

    /**
     * Sends the generationData to the view via websockets. Pre-condition is that the client is connected and registered
     * to the topic.
     * @param generationData Generation data which should be sent to the view
     */
//    @SneakyThrows
//    protected void drawGeneration(List<List<Boolean>> generationData) {
//        GenerationResponse generationResponse = new GenerationResponse();
//        generationResponse.setGenerationData(generationData);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String val = objectMapper.writeValueAsString(generationResponse);
//
//        simpMessagingTemplate.convertAndSend(PRINTING_GOL_GENERATION_TOPIC_PATH, val);
//    }

    @SneakyThrows
    protected void drawGeneration(boolean[][] generationData) {
        GenerationResponse generationResponse = new GenerationResponse();
        generationResponse.setGenerationData(generationData);

        ObjectMapper objectMapper = new ObjectMapper();
        String val = objectMapper.writeValueAsString(generationResponse);

        simpMessagingTemplate.convertAndSend(PRINTING_GOL_GENERATION_TOPIC_PATH, val);
    }

    @Data
    private static class GenerationResponse {
        private boolean[][] generationData;
    }
}
