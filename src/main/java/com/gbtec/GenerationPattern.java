package com.gbtec;

public class GenerationPattern {

    public GenerationPattern() {

    }

    public static void createGlider(Generation generation) {
        generation.getGeneration().get(0).get(0).createCell();
        generation.getGeneration().get(0).get(2).createCell();
        generation.getGeneration().get(1).get(1).createCell();
        generation.getGeneration().get(1).get(2).createCell();
        generation.getGeneration().get(2).get(1).createCell();
    }

    public static void createFlower(Generation generation, int generationSize) {
        int centralPoint = (generationSize - 1) / 2;
        generation.getGeneration().get(centralPoint - 1).get(centralPoint + 1).createCell();
        generation.getGeneration().get(centralPoint - 2).get(centralPoint + 1).createCell();
        generation.getGeneration().get(centralPoint - 3).get(centralPoint + 1).createCell();
        generation.getGeneration().get(centralPoint - 3).get(centralPoint).createCell();
        generation.getGeneration().get(centralPoint - 1).get(centralPoint - 1).createCell();
        generation.getGeneration().get(centralPoint - 2).get(centralPoint - 1).createCell();
        generation.getGeneration().get(centralPoint - 3).get(centralPoint - 1).createCell();

        generation.getGeneration().get(centralPoint + 1).get(centralPoint - 1).createCell();
        generation.getGeneration().get(centralPoint + 2).get(centralPoint - 1).createCell();
        generation.getGeneration().get(centralPoint + 3).get(centralPoint - 1).createCell();
        generation.getGeneration().get(centralPoint + 3).get(centralPoint).createCell();
        generation.getGeneration().get(centralPoint + 1).get(centralPoint + 1).createCell();
        generation.getGeneration().get(centralPoint + 2).get(centralPoint + 1).createCell();
        generation.getGeneration().get(centralPoint + 3).get(centralPoint + 1).createCell();
    }
}
