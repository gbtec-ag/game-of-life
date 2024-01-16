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
    private static boolean isInitialized = false;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public void init() {

        if (!isInitialized) {
            PropertiesLoader.loadProperties();
            display = new Display(simpMessagingTemplate);
            isInitialized = true;
        }

        if (isRunning)
            return;
        display.setGenerationData(Tools.generateRandomGenerationData(PropertiesLoader.getDisplayMatrixSize()));

    }

    @Override
    public void next() {
        if (!isInitialized)
            return;

        int matrixSize = PropertiesLoader.getDisplayMatrixSize();
        boolean[][] currentGenerationData = display.getCurrentGenerationData();
        boolean[][] nextGenerationData = new boolean[matrixSize][matrixSize];

        for (int x = 0; x < matrixSize; x++) {
            for (int y = 0; y < matrixSize; y++) {
                int livingNeighbourCount = Tools.getLivingNeighbourCount(x, y);
                if (currentGenerationData[x][y]) {
                    nextGenerationData[x][y] = livingNeighbourCount >= 2 && livingNeighbourCount <= 3;
                } else {
                    if (livingNeighbourCount == 3) {
                        nextGenerationData[x][y] = true;
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
        if (isRunning || !isInitialized)
            return;
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

    @Override
    public void clickCell(int x, int y) {
        if (isRunning)
            return;

        boolean[][] generationData = display.getCurrentGenerationData();

        generationData[x][y] = !generationData[x][y];

        display.setGenerationData(generationData);
    }
}