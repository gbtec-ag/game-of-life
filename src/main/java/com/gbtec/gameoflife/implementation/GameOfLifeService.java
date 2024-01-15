package com.gbtec.gameoflife.implementation;

import com.gbtec.gameoflife.framework.GameOfLifeCommandProxy;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {

    @Getter
    private static Display display;

    private static boolean isRunning = false;
    private static int delay = 0;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public void init() {

        PropertiesLoader.loadProperties();
        display = new Display(simpMessagingTemplate);

        display.setGenerationData(Tools.generateRandomGenerationData(Integer.parseInt(PropertiesLoader.getProperty("displayMatrixSize"))));

    }

    @Override
    public void next() {
        int matrixSize = Integer.parseInt(PropertiesLoader.getProperty("displayMatrixSize"));
        boolean[][] currentGenerationData = display.getCurrentGenerationData();
        boolean[][] nextGenerationData = new boolean[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int livingNeighbourCount = Tools.getLivingNeighbourCount(i, j);
                if (currentGenerationData[i][j]) {
                    nextGenerationData[i][j] = livingNeighbourCount >= 2 && livingNeighbourCount <= 3;
                } else {
                    if (livingNeighbourCount == 3) {
                        nextGenerationData[i][j] = true;
                    }
                }
            }
        }

        display.setGenerationData(nextGenerationData);
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public void play(int delayMs) {
        if (isRunning && delayMs <= 0 && delay == delayMs)
            return;
        delay = delayMs;
        isRunning = true;

        while (isRunning) {
            next();
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}