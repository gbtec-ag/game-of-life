package com.gbtec.gameoflife.custom;

import java.util.Random;

import com.gbtec.gameoflife.basic.GameOfLifeCommandProxy;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameOfLifeService extends GameOfLifeCommandProxy {

    public GameOfLifeService(SimpMessagingTemplate simpMessagingTemplate) {
        super(simpMessagingTemplate);
    }

    @Override
    public void init() {
        int dataSize = 8;
        boolean[][] generationData = new boolean[dataSize][dataSize];
        Random random = new Random();
        for (int y = 0; y < dataSize; y++) {
            for (int x = 0; x < dataSize; x++) {
                generationData[y][x] = random.nextBoolean();
            }
        }

        drawGeneration(generationData);
    }
}
