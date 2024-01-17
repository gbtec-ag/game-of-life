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
    private static SimpMessagingTemplate usedSimpMessagingTemplate;

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
        usedSimpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public void init() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        display.setGenerationData(Tools.generateRandomGenerationData(PropertiesLoader.getDisplayMatrixSize()), Display.Type.MAIN);

    }

    @Override
    public void next() {
        if (!isInitialized)
            return;

        int matrixSize = PropertiesLoader.getDisplayMatrixSize();
        boolean[][] currentGenerationData = display.getGenerationData(Display.Type.MAIN);
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

        display.setGenerationData(nextGenerationData, Display.Type.MAIN);
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

        boolean[][] generationData = display.getGenerationData(Display.Type.MAIN);

        generationData[x][y] = !generationData[x][y];

        display.setGenerationData(generationData, Display.Type.MAIN);
    }

    @Override
    public void onLoad() {
        resetGame();
    }

    @Override
    public void onConnect() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        resetGame();
    }

    public static void resetGame() {
        isRunning = false;

        if (!isInitialized) {
            PropertiesLoader.loadProperties();
            display = new Display(usedSimpMessagingTemplate);
            DataStorage.readFromFile();
            isInitialized = true;
        }

        display.setGenerationData(Tools.getEmptyGenerationData(), Display.Type.MAIN);

    }


    // Tab services


    @Override
    public void onStartConditionsSwap() {
        boolean[][] mainDisplayData = display.getGenerationData(Display.Type.MAIN);
        boolean[][] previewDisplayData = display.getGenerationData(Display.Type.PREVIEW);

        display.setGenerationData(previewDisplayData, Display.Type.MAIN);
        display.setGenerationData(mainDisplayData, Display.Type.PREVIEW);
    }

    @Override
    public void onStartConditionsClear() {
        display.setGenerationData(Tools.getEmptyGenerationData(), Display.Type.PREVIEW);
    }

    @Override
    public void onStartConditionsPreview(int storageId) {
        display.setGenerationData(DataStorage.getData(storageId).generationData(), Display.Type.PREVIEW);
    }

    @Override
    public void onStartConditionsSave(int storageId) {
        DataStorage.setData(storageId, display.getGenerationData(Display.Type.PREVIEW));
    }

    @Override
    public void onInitPreviewDisplay() {
        display.setGenerationData(Tools.getEmptyGenerationData(), Display.Type.PREVIEW);
    }
}