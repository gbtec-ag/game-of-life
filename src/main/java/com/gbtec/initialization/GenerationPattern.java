package com.gbtec.initialization;

import com.gbtec.model.Generation;

public class GenerationPattern {

    public GenerationPattern() {

    }

    public static void createGlider(Generation generation) {
        generation.getCell(0,0).createCell();
        generation.getCell(2,0).createCell();
        generation.getCell(1,1).createCell();
        generation.getCell(2,1).createCell();
        generation.getCell(1,2).createCell();
    }

    public static void createFlower(Generation generation, int generationSize) {
        int centralPoint = (generationSize - 1) / 2;
        generation.getCell(centralPoint+1,centralPoint-1).createCell();
        generation.getCell(centralPoint+1,centralPoint-2).createCell();
        generation.getCell(centralPoint+1, centralPoint-3).createCell();
        generation.getCell(centralPoint,centralPoint-3).createCell();
        generation.getCell(centralPoint-1,centralPoint-3).createCell();
        generation.getCell(centralPoint-1,centralPoint-2).createCell();
        generation.getCell(centralPoint-1,centralPoint-1).createCell();

        generation.getCell(centralPoint-1, centralPoint+1).createCell();
        generation.getCell(centralPoint-1,centralPoint+2).createCell();
        generation.getCell(centralPoint-1, centralPoint+3).createCell();
        generation.getCell(centralPoint,centralPoint+3).createCell();
        generation.getCell(centralPoint+1,centralPoint+1).createCell();
        generation.getCell(centralPoint+1,centralPoint+2).createCell();
        generation.getCell(centralPoint+1,centralPoint+3).createCell();
    }
}
